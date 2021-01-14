package com.example.uasmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response {

	@SerializedName("teams")
	private List<TeamsItem> teams;
	private List<LeagueItem> lig;

	public List<TeamsItem> getTeams(){
		return teams;
	}
	public List<LeagueItem> getLeague(){
		return lig;
	}
}