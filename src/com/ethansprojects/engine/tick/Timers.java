package com.ethansprojects.engine.tick;

/**
 * Timers and stopwatches.
 * @author ethansprojects
 */

public class Timers {
	
	private static long timer = System.currentTimeMillis();
	private static int seconds = 0;
	private static int swings = 0;
	
	public static boolean startCombatTimer(int hitDelay, String npc) {
		boolean delayReached = false;
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			seconds++;
			if ((hitDelay + 1) - seconds > 0) {
				System.out.println("Next hit attempt in: " + ((hitDelay + 1) - seconds));
			}
			if (seconds > hitDelay) {
				delayReached = true;
				seconds = 0;
				swings++;
				System.out.println("[" +swings+ "] " +npc+ " swings at player.");
			}
		}
		return delayReached;
	}
	
}
