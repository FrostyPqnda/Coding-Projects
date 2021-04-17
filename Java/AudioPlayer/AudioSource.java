import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 * 
 * @author brian
 *
 * Class AudioSource plays an audio file from within the
 * project folder
 */
public class AudioSource
{
	public void playAudio(String audioLocation)
	{
		try
		{
			File audioPath = new File(audioLocation);
			
			if(audioPath.exists())
			{
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
				JOptionPane.showMessageDialog(null, "Hit ok to pause");
				long clipTimePosition = clip.getMicrosecondPosition();
				clip.stop();
				
				JOptionPane.showMessageDialog(null, "Hit ok to resume");
				clip.setMicrosecondPosition(clipTimePosition);
				clip.start();
				
				JOptionPane.showMessageDialog(null, "Press OK to stop playing");
			}
			else
			{
				System.out.println("Can't find file");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
