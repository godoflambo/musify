package mx.com.coffeedev.spring.boot.springboorcoffeedev.models.general;

public class GeneralRS {

    private StatusInfo statusInfo;

    public GeneralRS(String status, String message) {
        this.statusInfo = new StatusInfo(status, message);
    }

    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo, String statusMessage) {
        this.statusInfo = new StatusInfo(statusInfo, statusMessage);
    }
}

class StatusInfo{

    private String status;
    private String message;

    public StatusInfo(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
