package com.sinius15.VACM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class VirtualAudioCable {
	
	private String name;
	
	private HashMap<String, String> arguments = new HashMap<String, String>();
	
	/**
	 * Creates a new {@link VirtualAudioCable}
	 * 
	 * @param windowName
	 *            the windowName of the AudioRepeater. windowName must exist of
	 *            letters.
	 */
	public VirtualAudioCable(String windowName) {
		if (!windowName.matches("[a-zA-Z]+"))
			throw new IllegalArgumentException("windowName must exist of letters.");
		arguments.put("WindowName:", "\"" + windowName + "\"");
		arguments.put("Autostart", null);
		this.name = windowName;
	}

	public void startAudioCable(){
		String[] args = new String[arguments.size()];
		args[0] = "start /min audiorepeater.exe";
		int i = 0;
		for(String key : arguments.keySet()){
			args[i] = "/" + key;
			if(arguments.get(i) != null){
				if(arguments.get(i).matches("[0-9]+")){
					args[i] += ": " + arguments.get(i);
				}else{
					args[i] += ": " + "\"" + arguments.get(i) + "\"";
				}
			}
		}
		
		ProcessBuilder builder = new ProcessBuilder(args);
		builder.directory(new File(VirtualAudioCableManager.getManager().frame.getExField().getText()));
		builder.redirectErrorStream(true);
		
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopAudioCable(){
		ProcessBuilder builder = new ProcessBuilder("start audiorepeater.exe /CloseInstance");
		builder.directory(new File(VirtualAudioCableManager.getManager().frame.getExField().getText()));
		builder.redirectErrorStream(true);
		
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setArgument(Argument a){
		arguments.put(a.getTitle(), a.getValue());
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
