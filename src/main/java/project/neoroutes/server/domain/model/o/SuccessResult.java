package project.neoroutes.server.domain.model.o;

public class SuccessResult extends Result {
    private static final SuccessResult successResultDto = new SuccessResult();

    public SuccessResult() {
        super(Status.SUCCESS);
    }

    public static SuccessResult getSuccessResult(){
        return successResultDto;
    }
}
