package by.mnk.htp.glotovs.msr.enums;

public enum UserType {
	USER("USER"),
	ADMIN("ADMIN");
	
	String type;
	
	private UserType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}


	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}
}
