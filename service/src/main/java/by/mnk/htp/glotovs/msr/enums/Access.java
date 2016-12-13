package by.mnk.htp.glotovs.msr.enums;

public enum Access {

	ACTIVE("true"),
	INACTIVE("false");

	private String state;
	
	private Access(final String state){
		this.state = state;
	}
	
	public String getState(){
		return this.state;
	}

	@Override
	public String toString(){
		return this.state;
	}

	public String getName(){
		return this.name();
	}


}
