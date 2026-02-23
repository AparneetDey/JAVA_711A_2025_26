package Feb21;

class Student {
    String uid;
    String name;
    int fineAmount;
    int currentBorrowCount;

    Student(String uid, String name, int fineAmount, int currentBorrowCount) {
        this.uid = uid;
        this.name = name;
        this.fineAmount = fineAmount;
        this.currentBorrowCount = currentBorrowCount;
    }

    void validatePolicy() {
        if (fineAmount > 0) {
            throw new IllegalStateException("Pending fine: " + fineAmount);
        }
        if (currentBorrowCount >= 2) {
            throw new IllegalStateException("Borrow limit reached");
        }
    }
}

class Asset {
    String assetId;
    String assetName;
    boolean available;
    int securityLevel;

    Asset(String assetId, String assetName, boolean available, int securityLevel) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.available = available;
        this.securityLevel = securityLevel;
    }

    void validatePolicy(String uid) {
        if (!available) {
            throw new IllegalStateException("Asset not available");
        }
        if (securityLevel == 3 && !uid.startsWith("KRG")) {
            throw new SecurityException("Restricted asset. UID not allowed.");
        }
    }
}

class CheckoutRequest {
    String uid;
    String assetId;
    int hoursRequested;

    CheckoutRequest(String uid, String assetId, int hoursRequested) {
        this.uid = uid;
        this.assetId = assetId;
        this.hoursRequested = hoursRequested;
    }
}

class ValidationUtil {

    static void validateUid(String uid) {
        if (uid == null || uid.length() < 8 || uid.length() > 12 || uid.contains(" ")) {
            throw new IllegalArgumentException("Invalid UID");
        }
    }

    static void validateAssetId(String assetId) {
        if (assetId == null || !assetId.startsWith("LAB-")) {
            throw new IllegalArgumentException("Invalid Asset ID");
        }
        String digits = assetId.substring(4);
        for (int i = 0; i < digits.length(); i++) {
            if (!Character.isDigit(digits.charAt(i))) {
                throw new IllegalArgumentException("Invalid Asset ID format");
            }
        }
    }

    static void validateHours(int hrs) {
        if (hrs < 1 || hrs > 6) {
            throw new IllegalArgumentException("Hours must be between 1 and 6");
        }
    }
}

class AssetStore {

    Asset[] assets;

    AssetStore(Asset[] assets) {
        this.assets = assets;
    }

    Asset findAsset(String assetId) {
        for (int i = 0; i < assets.length; i++) {
            if (assets[i].assetId.equals(assetId)) {
                return assets[i];
            }
        }
        throw new NullPointerException("Asset not found: " + assetId);
    }

    void markBorrowed(Asset a) {
        if (!a.available) {
            throw new IllegalStateException("Asset already borrowed");
        }
        a.available = false;
    }
}

class CheckoutService {

    Student[] students;
    AssetStore store;

    CheckoutService(Student[] students, AssetStore store) {
        this.students = students;
        this.store = store;
    }

    Student findStudent(String uid) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].uid.equals(uid)) {
                return students[i];
            }
        }
        throw new NullPointerException("Student not found: " + uid);
    }

    public String checkout(CheckoutRequest req)
            throws IllegalArgumentException, IllegalStateException,
            SecurityException, NullPointerException {

        ValidationUtil.validateUid(req.uid);
        ValidationUtil.validateAssetId(req.assetId);
        ValidationUtil.validateHours(req.hoursRequested);

        Student s = findStudent(req.uid);
        Asset a = store.findAsset(req.assetId);

        s.validatePolicy();
        a.validatePolicy(req.uid);

        // Realistic Constraints
        if (req.hoursRequested == 6) {
            System.out.println("Note: Max duration selected. Return strictly on time.");
        }

        if (a.assetName.contains("Cable") && req.hoursRequested > 3) {
            req.hoursRequested = 3;
            System.out.println("Policy applied: Cables max 3 hours. Updated to 3.");
        }

        store.markBorrowed(a);
        s.currentBorrowCount++;

        return "TXN-20260221-" + a.assetId + "-" + s.uid;
    }
}

class AuditLogger {

    static void log(String msg) {
        System.out.println("AUDIT: " + msg);
    }

    static void logError(Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
}

public class Main {

    public static void main(String[] args) {

        Student[] students = {
                new Student("KRG20281", "Aman", 0, 0),
                new Student("ABC12345", "Riya", 100, 0),
                new Student("KRG20282", "Raj", 0, 2)
        };

        Asset[] assets = {
                new Asset("LAB-101", "HDMI Cable", true, 1),
                new Asset("LAB-102", "Router", true, 3),
                new Asset("LAB-103", "Mouse", false, 1)
        };

        AssetStore store = new AssetStore(assets);
        CheckoutService service = new CheckoutService(students, store);

        CheckoutRequest[] requests = {
                new CheckoutRequest("KRG20281", "LAB-101", 4), // success
                new CheckoutRequest("KRG20281", "LAB-XYZ", 2), // invalid assetId
                new CheckoutRequest("ABC12345", "LAB-102", 2)  // fine policy fail
        };

        for (CheckoutRequest req : requests) {

            try {
                String receipt = service.checkout(req);
                System.out.println("SUCCESS: " + receipt);

            } catch (IllegalArgumentException e) {
                AuditLogger.logError(e);

            } catch (NullPointerException e) {
                AuditLogger.logError(e);

            } catch (SecurityException e) {
                AuditLogger.logError(e);

            } catch (IllegalStateException e) {
                AuditLogger.logError(e);

            } finally {
                AuditLogger.log("Attempt finished for UID=" + req.uid +
                        ", Asset=" + req.assetId);
                System.out.println("--------------------------------");
            }
        }
    }
}
