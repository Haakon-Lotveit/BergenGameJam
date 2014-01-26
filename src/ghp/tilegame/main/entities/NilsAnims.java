package ghp.tilegame.main.entities;

public class NilsAnims {
	// Optimaliserin; Endre arrayene til BufferImage
	public static String[] walk_F = {"resources/sprites/nils/Nils_Walk_F01.png",
		"resources/sprites/nils/Nils_Walk_F02.png",
		"resources/sprites/nils/Nils_Walk_F03.png",};
	
	public static String[] walk_B = {"resources/sprites/nils/Nils_Walk_B01.png",
		"resources/sprites/nils/Nils_Walk_B02.png",
		"resources/sprites/nils/Nils_Walk_B03.png",
	};
	
	public static String[] walk_L = {"resources/sprites/nils/Nils_Walk_L01.png",
		"resources/sprites/nils/Nils_Walk_L02.png",
		"resources/sprites/nils/Nils_Walk_L03.png",
	};
	
	public static String[] walk_R = {"resources/sprites/nils/Nils_Walk_R01.png",
		"resources/sprites/nils/Nils_Walk_R02.png",
		"resources/sprites/nils/Nils_Walk_R03.png",
	};
	
	public static String[][] attackAnim = {	// Ikke byttet enda
		{"resources/sprites/HanKisen_Anims/HanKisen_Attack_F01.png", "resources/sprites/HanKisen_Anims/HanKisen_Attack_F02.png"},
		{"resources/sprites/HanKisen_Anims/HanKisen_Attack_B01.png", "resources/sprites/HanKisen_Anims/HanKisen_Attack_B02.png"},
		{"resources/sprites/HanKisen_Anims/HanKisen_Attack_L01.png", "resources/sprites/HanKisen_Anims/HanKisen_Attack_L02.png"},
		{"resources/sprites/HanKisen_Anims/HanKisen_Attack_R01.png", "resources/sprites/HanKisen_Anims/HanKisen_Attack_R02.png"},
	};
	
	public String getAnims(char animType, char charAngle, int frame){
		if (animType == 'W'){		// Walk
			if (charAngle == 'F')
				return walk_F[frame];
			else if (charAngle == 'B')
				return walk_B[frame];
			else if (charAngle == 'L')
				return walk_L[frame];
			else if (charAngle == 'R')
				return walk_R[frame];
			else // return standard position
				return walk_F[frame];
			}
		else if (animType == 'A'){	// Attack
			if (charAngle == 'F')
				return attackAnim[0][frame];
			else if (charAngle == 'B')
				return attackAnim[1][frame];
			else if (charAngle == 'L')
				return attackAnim[2][frame];
			else if (charAngle == 'R')
				return attackAnim[3][frame];
			else // return standard position
//				return walk_F[frame];
				return attackAnim[3][frame];
		}
		else // return standard position
			return walk_F[frame];
	}
		
		

}
