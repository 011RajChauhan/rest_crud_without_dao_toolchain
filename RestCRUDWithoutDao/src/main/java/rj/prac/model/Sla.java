package rj.prac.model;

public class Sla {

	private String serviceId;
	private String goalTime;
	private String moduleId;
	private String goalTypeId;
	
	public Sla(String serviceId, String goalTime, String moduleId, String goalTypeId) {
		this.serviceId = serviceId;
		this.goalTime = goalTime;
		this.moduleId = moduleId;
		this.goalTypeId = goalTypeId;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getGoalTime() {
		return goalTime;
	}
	public void setGoalTime(String goalTime) {
		this.goalTime = goalTime;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getGoalTypeId() {
		return goalTypeId;
	}
	public void setGoalTypeId(String goalTypeId) {
		this.goalTypeId = goalTypeId;
	}
	
	
}
