package com.ethansprojects.features.dialogue;

import com.ethansprojects.engine.graphics.Font;
import com.ethansprojects.engine.graphics.Screen;
import com.ethansprojects.engine.graphics.Sprite;
import com.ethansprojects.engine.Zionus;

/**
 * Dialogues.
 * @author ethansprojects
 */

public class Dialogue {
	
	public static int fontXscreenLoc = 327, fontYscreenLoc = 375;
	private static int iconXscreenLoc = 256, iconYscreenLoc = 368;
	
	public static int ethanTutorialDialogueState = -1;
	
	public static Font font_system_black = new Font("/fonts/system_black.png", 8);
	
	public Dialogue(String text) {
		// this.text = text;
	}
	
	public static void dialoguePrompt(Screen screen) {
		screen.renderSprite(370, 370, Sprite.space_talk, false);
	}
	
	public static void sendMessage(String message, Screen screen) {
		screen.renderSprite(232, 348, Sprite.dialogue_box_far_left, false);
		screen.renderSprite(332, 348, Sprite.dialogue_box_mid_left, false);
		screen.renderSprite(432, 348, Sprite.dialogue_box_far_right, false);
		screen.renderSprite(532, 348, Sprite.dialogue_box_mid_right, false);
		font_system_black.render("Notification", 388, 358, screen);
		font_system_black.render("\n" + message, fontXscreenLoc, fontYscreenLoc, screen);
		screen.renderSprite(iconXscreenLoc, iconYscreenLoc, Sprite.notification_icon, false);
	}
	
	public static void sendMessageWithIcon(String message, Sprite iconImage, Screen screen) {
		screen.renderSprite(232, 348, Sprite.dialogue_box_far_left, false);
		screen.renderSprite(332, 348, Sprite.dialogue_box_mid_left, false);
		screen.renderSprite(432, 348, Sprite.dialogue_box_far_right, false);
		screen.renderSprite(532, 348, Sprite.dialogue_box_mid_right, false);
		font_system_black.render("Notification", 388, 358, screen);
		font_system_black.render("\n" + message, fontXscreenLoc, fontYscreenLoc, screen);
		screen.renderSprite(iconXscreenLoc, iconYscreenLoc, iconImage, false);
	}
	
	public static void dialogueBox(String NPCname, Screen screen) {
		screen.renderSprite(232, 348, Sprite.dialogue_box_far_left, false);
		screen.renderSprite(332, 348, Sprite.dialogue_box_mid_left, false);
		screen.renderSprite(432, 348, Sprite.dialogue_box_far_right, false);
		screen.renderSprite(532, 348, Sprite.dialogue_box_mid_right, false);
		
		if (Zionus.ACT == 0) {
			prologueDialogues(screen);
		}
		
		renderDialogueCloseups(NPCname, screen);
		
	}
	
	private static void renderDialogueCloseups(String speakersName, Screen screen) {
		int centeredSpeakerName = (436 - ((int) (8 * speakersName.length() / 2)));
		
		speakersName = speakersName.toLowerCase();
		Sprite icon;
		
		switch (speakersName) {
			case "notification":
				icon = Sprite.notification_icon;
				break;
				
			case "command":
				icon = Sprite.commands_icon;
				break;
				
			case "nephyr":
				icon = Sprite.nephyr_closeup;
				break;
			
			default:
				icon = Sprite.ethan_closeup;
				break;
		}

		String firstLetter = speakersName.substring(0, 1).toUpperCase();
		String lastLetters = speakersName.substring(1);
		
		speakersName = firstLetter + lastLetters;
		
		font_system_black.render(speakersName, centeredSpeakerName, 358, screen);
		screen.renderSprite(iconXscreenLoc, iconYscreenLoc, icon, false);
	}
	
	private static void prologueDialogues(Screen screen) {
		
		switch (Zionus.ACT_stage) {
			case 1:
				font_system_black.render("\nWhere am I?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 4:
				font_system_black.render("\nHelp! Demons are invading!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 5:
				font_system_black.render("\nYeah... riiight.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 6:
				font_system_black.render("\nThe ground shakes..", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 7:
				font_system_black.render("\nTold you!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 8:
				font_system_black.render("\nCome with me, there's no time!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 10: // use your spellbook
				font_system_black.render("\nUse your spellbook to cast a spell\non that demon!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 11: // huh?
				font_system_black.render("\nHuh?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 12: // oh come on
				font_system_black.render("\nOh come on, you're The Chosen One!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 13: // you sense a guiding force
				font_system_black.render("\nYou sense a guiding force...", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 15: // eeek
				font_system_black.render("\nEeek!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 19: // lets pretend that didnt happen
				font_system_black.render("\nLet's just pretend that didn't happen.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 21: // this should help
				font_system_black.render("\nThis should help. Here, take back your gear.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 22: // nephyr hands you your gear
				font_system_black.render("\nNephyr hands you your gear.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 23: // hello again old friend
				font_system_black.render("\nHello again, old wand.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 25: // i can't wear this gear
				font_system_black.render("\nI can't wear this gear!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 26: // oh my you're not ready for that anymore
				font_system_black.render("\nOh my. The Guiding Force may not think you're ready for it anymore.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 27: // you may have suffered significant memory loss
				font_system_black.render("You may have suffered significant memory loss,\nwhich has set you back quite a bit.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 28: // wait, what?!
				font_system_black.render("\nWait, what?!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 29: // ground shakes again
				font_system_black.render("\nYou feel the ground below your feet shake..", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 30: // more demons
				font_system_black.render("\nMore demons! Here, use this novice gear for now!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 31: // nephyr gives jamison novice weapon and gear
				font_system_black.render("\nNephyr hands you some back up novice gear and a weapon.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 33: // and we're off
				font_system_black.render("\nAnd we're off!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 36: // remind me why you were so chicken 10 secs ago
				font_system_black.render("\nRemind me why you were so chicken just a minute ago?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 37: // i haven't used this gear in years
				font_system_black.render("I haven't used this gear in years! I must\n have left it in that chest ages ago.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 38: // tell me what is going on
				font_system_black.render("\nCan you tell me what is going on?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 39: // ground shakes again
				font_system_black.render("\nThe ground begins to shake again...", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 40: // that may have to wait
				font_system_black.render("\nThat may have to wait!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 42: // been years since ive got this much action
				font_system_black.render("Holy smokes. It's been years since\nI've gotten this much action.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 43: // thats the last of them
				font_system_black.render("\nFortunately, I think that's the last of them for now.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 44: // come with me to hut
				font_system_black.render("\nCome with me, I'll explain everything back at my hut.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(385, 380, Sprite.exit_image, false);
				break;
			
			case 47: // whats going on? you said...
				font_system_black.render("\nWhat happened back there? You said\nthat I suffered significant memory loss?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 48: // that would be my guess
				font_system_black.render("Ah, yes. That would be my guess,\nanyway. You didn't fight like before..", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 49: // this morning
				font_system_black.render("This morning, you and I were at\nmy hut making a very special potion.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 50: // unfortunately things went wrong
				font_system_black.render("Unfortunately, something went wrong, and...", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 51: // and?
				font_system_black.render("\nAnd?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 52: // solution blew up in your face
				font_system_black.render("The solution kind of blew up in your face.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 53: // WHAT
				font_system_black.render("\nWHAT?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 54: // i didnt mean to
				font_system_black.render("It was an accident! If only there was no\nacid influx from that pepper-oxide...", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 55: // so how did we
				font_system_black.render("So how did we end up in that abandoned hospital?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 56: // town got raided
				font_system_black.render("Ah, yes. Shortly after you were out cold,\n the town was raided by that cult of demons.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 57: // they seemed to be after
				font_system_black.render("\nThey seemed to be after something of mine...", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 58: // i carried you into hospital
				font_system_black.render("\nAnyhow, I carried you from my hut to the hospital.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 59: // after removing your gear
				font_system_black.render("After removing your gear I began to operate\non you - then I heard a bang at the entrance.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 60: // I barracaded them off
				font_system_black.render("\nI barracaded them off as best as I could,\nthen came to check back on you..", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 61: // thats when you woke up
				font_system_black.render("\nAnd that's when you woke up.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 62: // so you tried to
				font_system_black.render("Let's back up - you tried to\nmake a fancy potion and exploded me into\nunconsciousness which caused me to lose my memory?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 63: // it appears so
				font_system_black.render("\nIt...appears so.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 64: // sadly you may need weeks
				font_system_black.render("Sadly, you may need weeks of training\n to get back to full speed.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 65: // i'm terribly sorry
				font_system_black.render("\nI'm terribly sorry.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 66: // grr
				font_system_black.render("Grr..", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 67: // fear not
				font_system_black.render("\nBut fear not! You are The Chosen One!", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 68: // this town has been calling me a lunatic
				font_system_black.render("This town has been calling me a lunatic for years.\nNo one believes you are, but then again no one believed\nme when I said a cult of demons would attack.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 70: // the chosen one what does that mean 
				font_system_black.render("'The Chosen One'? What does that even mean?", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 71: // you seemed to have forgotten a lot
				font_system_black.render("Oh my... You seemed to have forgotten a lot.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
			
			case 72: // chris hansen, this may take awhile
				font_system_black.render("Why don't you sit down, this may take a while.", fontXscreenLoc, fontYscreenLoc, screen);
				screen.renderSprite(532, 380, Sprite.continue_image, false);
				break;
		}
		
		//		if (ethanTutorialDialogueState == 0) {
		//			NPCisTalking = true;
		//			notification = false;
		//			font_system_black.render("Welcome to Zionus, " + Zionus.username + "!\n"
		//					+ "You are currently in the tutorial.\n"
		//					+ "If you want to skip the tutorial,\n"
		//					+ "you may choose to do so now.", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 1) {
		//			NPCisTalking = false;
		//			notification = true;
		//			
		//			font_system_black.render("Would you like to skip the tutorial?\n"
		//					+ "\n"
		//					+ "     NO         YES", fontXscreenLoc, fontYscreenLoc, screen);
		//			screen.renderSprite(380, 370, Sprite.enter, false);
		//			screen.renderSprite(475, 367, Sprite.tab, false);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 2) {
		//			notification = false;
		//			NPCisTalking = true;
		//			font_system_black.render("Awesome! Well firstly, I'm Ethan. I\n"
		//					+ "am the lead developer of Zionus, &\n"
		//					+ "and I'm here to take you on a tour of\n"
		//					+ "the game!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 3) {
		//			NPCisTalking = false;
		//			font_system_black.render("Wait, I'm actually talking to the\n"
		//					+ "lead developer right now?", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 4) {
		//			NPCisTalking = true;
		//			font_system_black.render("Kind of! Ethan coded my intelligence\n"
		//					+ "& sent me here in NPC form to help\n"
		//					+ "you get going on your very own\n"
		//					+ "adventure in Zionus! Are you ready?!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 5) {
		//			NPCisTalking = false;
		//			font_system_black.render("Yes, I am ready!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 6) {
		//			NPCisTalking = true;
		//			font_system_black.render("Great! First, let's try walking\n"
		//					+ "around. You cannot move if you are\n"
		//					+ "talking to an NPC.Exit the dialogue\n"
		//					+ "then use WASD or the arrow keys.", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 7) {
		//			NPCisTalking = true;
		//			font_system_black.render("Well done!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 8) {
		//			NPCisTalking = true;
		//			font_system_black.render("Now I'm going to teach you about\n"
		//					+ "projectiles. Warmages and Assassins\n"
		//					+ "can shoot projectiles. Warriors and\n"
		//					+ "Tanks cannot, as they are melee.", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 9) {
		//			NPCisTalking = true;
		//			font_system_black.render("Assassins can shoot arrows and other\n"
		//					+ "ranged-only projectiles. Warmages can\n"
		//					+ "shoot certain projectiles granted by\n"
		//					+ "different spells.", fontXscreenLoc, 375, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 10) {
		//			NPCisTalking = true;
		//			font_system_black.render("However, for the sake of the tutorial\n"
		//					+ "I'll allow you to shoot projectiles\n"
		//					+ "regardless of your selected class.\n"
		//					+ "I'll give you an unlimited amount...", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 11) {
		//			NPCisTalking = true;
		//			font_system_black.render("to practice around with for now.Just\n"
		//					+ "click around you to shoot them!\n"
		//					+ "Remember, you cannot shoot or walk\n"
		//					+ "while talking to an NPC!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 12) {
		//			NPCisTalking = true;
		//			font_system_black.render("Good!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 13) {
		//			NPCisTalking = true;
		//			font_system_black.render("Also, when you're walking, holding\n"
		//					+ "down SHIFT will allow you to move\n"
		//					+ "slightly faster than normal. Either\n"
		//					+ "shift button works for this.", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 14) {
		//			NPCisTalking = true;
		//			font_system_black.render("I am now going to introduce you to\n"
		//					+ "the HUD. The HUD includes key info\n"
		//					+ "that pertains to your character such\n"
		//					+ "as your backpack, skills, map, etc.", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 15) {
		//			NPCisTalking = true;
		//			font_system_black.render("When you hover your mouse over the\n"
		//					+ "HUD your cursor will change to a\n"
		//					+ "slightly darker color. Try clicking\n"
		//					+ "on a few items on the HUD now!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 16) {
		//			NPCisTalking = true;
		//			font_system_black.render("Good job - now let's talk about\n"
		//					+ "combat! To teach you about combat,\n"
		//					+ "we'll practice on some test NPCs!\n"
		//					+ "Exit the dialogue then follow me!", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 18) {
		//			NPCisTalking = true;
		//			font_system_black.render("The first thing to consider about\n"
		//					+ "combat is your character class.Like\n"
		//					+ "I said before, assassins use ranged,\n"
		//					+ "warmages use magic, then tanks &", fontXscreenLoc, fontYscreenLoc, screen);
		//		}
		//		
		//		if (ethanTutorialDialogueState == 19) {
		//			NPCisTalking = true;
		//			if (Zionus.user_class.equalsIgnoreCase("warrior") || Zionus.user_class.equalsIgnoreCase("tank")) {
		//				
		//				font_system_black.render("warriors use melee for attacking.\n"
		//						+ "Since you are a " + Zionus.user_class + ", I will be\n"
		//								+ "teaching you how to fight with only\n"
		//								+ "melee. Let's try first on a goblin.", fontXscreenLoc, fontYscreenLoc, screen);
		//			} else if (Zionus.user_class.equalsIgnoreCase("warmage")) {
		//				font_system_black.render("warriors use melee for attacking.\n"
		//						+ "Since you are a " + Zionus.user_class + ", I will be\n"
		//								+ "teaching you how to fight with only\n"
		//								+ "magic. Let's try first on a goblin.", fontXscreenLoc, fontYscreenLoc, screen);
		//			} else if (Zionus.user_class.equalsIgnoreCase("assassin")) {
		//				
		//				font_system_black.render("warriors use melee for attacking.\n"
		//						+ "Since you are a " + Zionus.user_class + ", I will be\n"
		//						+ "teaching you how to fight with only\n"
		//						+ "ranged. Let's try first on a goblin.", fontXscreenLoc, fontYscreenLoc, screen);
		//			} else {
		//				font_system_black.render("ERROR LOADING DIALOGUE: USER HAS NO\n"
		//						+ "SELECTED CLASS OR HAS INVALID CLASS\n"
		//						+ "NAME", fontXscreenLoc, fontYscreenLoc, screen);
		//			}
		//			
		//			
		//		}
	}
	
}
