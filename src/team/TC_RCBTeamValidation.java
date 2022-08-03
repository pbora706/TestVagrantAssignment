package team;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import payload.Payload;

public class TC_RCBTeamValidation {

	JsonPath js = new JsonPath(Payload.teamRCB());
	int playerCount = js.getInt("player.size()");

	@Test
	public void noOfForeignPlayers() {

		// print Team name
		String teamName = js.getString("name");
		System.out.println("Team Name: " + teamName);

		// print no of all players
		System.out.println("Team Size: " + playerCount);

		System.out.println("TC -1 - validate that team has only 4 foreign players");
		int count = 0;
		System.out.println("Details of Foreign players");
		for (int i = 0; i < playerCount; i++) {
			String playerCountry = js.get("player[" + i + "].country");
			if (!"India".equals(playerCountry)) {
				count++;
				String foreignPlayername = js.get("player[" + i + "].name");
				System.out.println(foreignPlayername + " : " + playerCountry);
			}
		}
		System.out.println("No of Foreign player " + count);
		Assert.assertEquals(count, 4);
	}

	@Test
	public void noOfWicketKeeper() {
		System.out.println("TC -2 - validate that there is atleast one wicket keeper");
		int wkCount = 0;
		for (int i = 0; i < playerCount; i++) {
			String playerRole = js.get("player[" + i + "].role");
			if ("Wicket-keeper".equals(playerRole)) {
				wkCount++;
				String wicketKeeperName = js.get("player[" + i + "].name");
				System.out.println("Wicket Keeper Name " + wicketKeeperName);
			}
		}
		if (wkCount == 0) {
			System.out.println("No Wicket Keeper on the Team");
		}
	}

}
