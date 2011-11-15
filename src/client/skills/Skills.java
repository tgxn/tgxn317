package client.skills;

public class Skills {

    public static int skillsCount = 25;
    public static String skillNames[] = {
        "attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking", "woodcutting", "fletching",
        "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility", "thieving", "slayer", "farming",
        "runecraft", "construction", "hunting", "summoning", "-unused-"
    };
    public static boolean skillEnabled[] = {
        true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, false,
        true, true, true, true, false
    };
}
