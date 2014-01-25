package ghp.tilegame.main.entities;

public class PlayerAnims {
	// Optimaliserin; Endre arrayene til BufferImage
	public static String[] walk_F = {"resources/sprites/HanKisen_Anims/HanKisen_Walk_F01.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_F02.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_F02.png"};
	
	public static String[] walk_B = {"resources/sprites/HanKisen_Anims/HanKisen_Walk_B01.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_B02.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_B02.png"
	};
	
	public static String[] walk_L = {"resources/sprites/HanKisen_Anims/HanKisen_Walk_L01.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_L02.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_L02.png"
	};
	
	public static String[] walk_R = {"resources/sprites/HanKisen_Anims/HanKisen_Walk_R01.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_R02.png",
		"resources/sprites/HanKisen_Anims/HanKisen_Walk_R02.png"
	};
	
	public String getAnims(char charAngle, int frame, int seq){
		if (charAngle == 'F')
			return walk_F[frame];
		else if (charAngle == 'B')
			return walk_B[frame];
		else if (charAngle == 'L')
			return walk_L[frame];
		else if (charAngle == 'R')
			return walk_R[frame];
		else
			return walk_F[frame];
	}

}
