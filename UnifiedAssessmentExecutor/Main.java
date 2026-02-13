package UnifiedAssessmentExecutor;

abstract class AbstractAssessmentFlow {
    private void validate() {
        System.out.println("Validate");
    }
    private void prepare() {
        System.out.println("Prepare");
    }
    abstract void evaluate();
    private void publishResult() {
        System.out.println("Publish result");
    }

    public final void executeAssessment() {
        validate();
        prepare();
        evaluate();
        publishResult();
    }
}

interface AutoAssessment {
    default void evaluate() {
        System.out.println("Auto Evaluation");
    };    
}

interface ManualAssessment {
    default void evaluate() {
        System.out.println("Manual Evaluation");
    };
}

class UnifiedAssessmentExecutor extends AbstractAssessmentFlow implements AutoAssessment, ManualAssessment {
    boolean isProctored = false;

    public UnifiedAssessmentExecutor(boolean isProctored) {
        this.isProctored = isProctored;
    }

    public void evaluate() {
        if(isProctored) {
            AutoAssessment.super.evaluate();
        } else {
            ManualAssessment.super.evaluate();
        }
    }
}

public class Main {
    public static void main(String args[]) {
        UnifiedAssessmentExecutor executor = new UnifiedAssessmentExecutor(false);
        executor.executeAssessment();
    }
}
