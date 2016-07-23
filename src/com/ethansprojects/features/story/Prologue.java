package com.ethansprojects.features.story;

import java.awt.event.KeyEvent;
import com.ethansprojects.engine.Zionus;


/**
 * Prologue storyline events.
 * @author ethansprojects
 */

public class Prologue {
	
/*	
 * Commenting this out for now.
 * 
 * private static void nephyrActions() {
		switch (Zionus.ACT_stage) {
			
			case 3:
				Zionus.nephyr.chasePlayer(50, 2);
				if (Zionus.nephyr.getXProximityToPlayer() <= 250) {
					Zionus.ACT_stage = 4;
				}
				break;
			
			case 9:
				if (Zionus.nephyr.x > 600) {
					Zionus.nephyr.xa = -1;
				}
				break;
				
			case 15:
				if (Zionus.nephyr.x < 1200) {
					Zionus.nephyr.xa = 4;
				} else if (Zionus.nephyr.x >= 1200) {
					Zionus.nephyr.x = 1856;
					Zionus.nephyr.y = 224;
					if (Zionus.ACT_stage == 15) {
						// To get rid of dialogue box if player hasn't already
						Zionus.ACT_stage = 16;
					}
				}
				break;
				
			case 16:
				if (Zionus.nephyr.x < 1200) {
					Zionus.nephyr.xa = 4;
				} else if (Zionus.nephyr.x >= 1200) {
					Zionus.nephyr.x = 1856;
					Zionus.nephyr.y = 224;
				}
				break;
				
			case 17:
				Zionus.nephyr.x = 1212;
				Zionus.nephyr.y = 138;
				break;
		}
	} */
	
	public static void prologueActions() {
		
		switch (Zionus.ACT_stage) {
			case 0:
				Zionus.player.keyboardInput = false;
				if (Zionus.oneSecondTimer == 8) {
					Zionus.player.setDirection("left");
				} else if (Zionus.oneSecondTimer == 10) {
					Zionus.player.setDirection("right");
				} else if (Zionus.oneSecondTimer == 12) {
					Zionus.player.setDirection("down");
				} else if (Zionus.oneSecondTimer == 13) {
					Zionus.ACT_stage = 1;
				}
				break;
			
			case 1: // where am i
				Zionus.player.personSpeaking = Zionus.username;
				Zionus.player.keyboardInput = true;
				Zionus.player.frozen = true;
				if (Zionus.player.input.keyTyped(KeyEvent.VK_SPACE)) {
					Zionus.ACT_stage = 2;
					Zionus.player.frozen = false;
				}
				break;
			
			case 2:
				Zionus.player.personSpeaking = "";
				Zionus.player.keyboardInput = true;
				Zionus.player.frozen = false;
				if (Zionus.player.x <= 1202) {
					Zionus.ACT_stage = 3;
				}
				break;
			
			case 3:
				Zionus.player.setDirection("left");
				Zionus.player.frozen = true;
				Zionus.nephyr.chasePlayer(50, 2);
				if (Zionus.nephyr.getXProximityToPlayer() <= 250) {
					Zionus.ACT_stage = 4;
				}
				break;
			
			case 4: // Demons are invading the house
				Zionus.player.personSpeaking = "Nephyr";
				Zionus.player.keyboardInput = true;
				Zionus.player.frozen = true;
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 5;
				}
				break;
			
			case 5: // Yeah right
				Zionus.player.personSpeaking = Zionus.username;
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 6;
				}
				break;
			
			case 6: // Ground shakes
				Zionus.player.personSpeaking = "notification";
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 7;
				}
				break;
			
			case 7: // Told you
				Zionus.player.personSpeaking = "Nephyr";
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 8;
				}
				break;
			
			case 8: // Come with me
				if (Zionus.player.input.keyTyped(KeyEvent.VK_SPACE)) {
					Zionus.ACT_stage = 9;
				}
				break;
			
			case 9:
				if (Zionus.nephyr.x > 600) {
					Zionus.nephyr.xa = -1;
				}
				Zionus.player.personSpeaking = "";
				Zionus.player.keyboardInput = true;
				Zionus.player.frozen = false;
				if (Zionus.player.x <= 460) {
					Zionus.ACT_stage = 10;
				}
				break;
			
			case 10: // use your spellbook
				Zionus.player.personSpeaking = "Nephyr";
				Zionus.player.frozen = true;
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 11;
				}
				
				break;
			
			case 11: // huh?
				Zionus.player.personSpeaking = Zionus.username;
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 12;
				}
				break;
			
			case 12: // oh come on
				Zionus.player.personSpeaking = "Nephyr";
				if (Zionus.player.input.keyTyped(KeyEvent.VK_ENTER)) {
					Zionus.ACT_stage = 13;
				}
				break;
			
			case 13: // you sense a guiding force
				Zionus.player.personSpeaking = "notification";
				if (Zionus.player.input.keyTyped(KeyEvent.VK_SPACE)) {
					Zionus.ACT_stage = 14;
				}
				break;
			
			case 14: // demon uses ignite and heads towards player
				Zionus.player.personSpeaking = "";
				Zionus.player.frozen = false;
				break;
			
			case 15: // eek
				Zionus.player.personSpeaking = "Nephyr";
				
				if (Zionus.nephyr.x < 1200) {
					Zionus.nephyr.xa = 4;
				} else if (Zionus.nephyr.x >= 1200) {
					Zionus.nephyr.x = 1856;
					Zionus.nephyr.y = 224;
					if (Zionus.ACT_stage == 15) {
						// To get rid of dialogue box if player hasn't already
						Zionus.ACT_stage = 16;
					}
				}
				
				if (Zionus.player.input.keyTyped(KeyEvent.VK_SPACE)) {
					Zionus.ACT_stage = 16;
				}
				
				break;
			
			case 16: // highlights how to use spellbook and nephyr runs away
				Zionus.player.personSpeaking = "";
				
				if (Zionus.nephyr.x < 1200) {
					Zionus.nephyr.xa = 4;
				} else if (Zionus.nephyr.x >= 1200) {
					Zionus.nephyr.x = 1856;
					Zionus.nephyr.y = 224;
				}
				
				if (Zionus.player.x > 1200 && (Zionus.player.y > 82 || Zionus.player.y < 160)) {
					Zionus.player.sendMessage = "Nephyr has locked the door tight.";
				} else {
					Zionus.player.sendMessage = "";
				}
				// highlights how to use spellbook
				break;
			
			case 17: // player attacks demon
				
				break;
			
			case 18: // player kills demon
				Zionus.nephyr.x = 1212;
				Zionus.nephyr.y = 138;
				break;
			
			case 19: // lets pretend that didnt happen
				break;
			
			case 20: // nephyr walks towards chest and unlocks it
				break;
			
			case 21: // this should help
				break;
			
			case 22: // nephyr hands you your gear
				break;
			
			case 23: // hello again old friend
				break;
			
			case 24: // highlights how to equip
				break;
			
			case 25: // i can't wear this gear
				break;
			
			case 26: // oh my you're not ready for that anymore
				break;
			
			case 27: // you may have suffered significant memory loss
				break;
			
			case 28: // wait, what?!
				break;
			
			case 29: // ground shakes again
				break;
			
			case 30: // more demons
				break;
			
			case 31: // nephyr gives jamison novice weapon and gear
				break;
			
			case 32: // highlights again how to equip gear and waits until you equip it
				break;
			
			case 33: // and we're off
				break;
			
			case 34: // nephyr leaves room, when player leaves he is teleported to next room
				// checkpoint!
				break;
			
			case 35: // nephyr is attacking demons and doing most of the damage. player can attack if he wants
				// case 36 when all these demons die
				break;
			
			case 36: // remind me why you were so chicken 10 secs ago
				break;
			
			case 37: // i haven't used this gear in years
				break;
			
			case 38: // tell me what is going on
				break;
			
			case 39: // ground shakes again
				break;
			
			case 40: // that may have to wait
				// case 41 when player enters next room
				break;
			
			case 41: // split up demons
				// case 42 when all THOSE demons are dead
				break;
			
			case 42: // been years since ive got this much action
				break;
			
			case 43: // thats the last of them
				break;
			
			case 44: // come with me to hut
				// case 45 when player leaves building
				break;
			
			case 45: // load new level for town??
				break;
			
			case 46: // player auotmatically follows nephyr to his hut
				// case 47 when automatically goes through door
				break;
			
			case 47: // whats going on? you said...
				break;
			
			case 48: // that would be my guess
				break;
			
			case 49: // this morning
				break;
			
			case 50: // unfortunately things went wrong
				break;
			
			case 51: // and?
				break;
			
			case 52: // solution blew up in your face
				break;
			
			case 53: // WHAT
				break;
			
			case 54: // i didnt mean to
				break;
			
			case 55: // so how did we
				break;
			
			case 56: // town got raided
				break;
			
			case 57: // they seemed to be after
				break;
			
			case 58: // i carried you into hospital
				break;
			
			case 59: // after removing your gear
				break;
			
			case 60: // I barracaded them off
				break;
			
			case 61: // thats when you woke up
				break;
			
			case 62: // so you tried to
				break;
			
			case 63: // it appears so
				break;
			
			case 64: // sadly you may need weeks
				break;
			
			case 65: // i'm terribly sorry
				break;
			
			case 66: // grr
				break;
			
			case 67: // fear not
				break;
			
			case 68: // this town has been calling me a lunatic
				break;
			
			case 69: // no one believes you are
				break;
			
			case 70: // the chosen one what does that mean 
				break;
			
			case 71: // you seemed to have forgotten a lot
				break;
			
			case 72: // chris hansen, this may take awhile
				break;
			
			case 73: // fade out to act I???
				break;
		
		}
	}
	
}
