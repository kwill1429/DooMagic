
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import alchemist.AlchemistGlobal;
import alchemist.AlchemistOptionPanel;
import alchemist.BankTaskAlchemy;
import alchemist.AlchemyTask;

import com.epicbot.api.ActiveScript;
import com.epicbot.api.GameType;
import com.epicbot.api.Manifest;
import com.epicbot.event.listeners.PaintListener;
	@Manifest(game=GameType.RS3, name="TheAlchemist", author="Doomboy5888", description="Handles all your alchemy, teleporting and superheating needs", version=0.5)

public class TheAlchemist extends ActiveScript implements PaintListener 
{
	private BankTaskAlchemy bankTask;
	private AlchemyTask alchemyTask;
	public AlchemistOptionPanel optionPanel;
		
	@Override
	public boolean onStart() {
		AlchemistGlobal.script = this;
		this.setName("The Alchemist");
		setupOptionPanel();
		AlchemistGlobal.timeStart = getStartTime();
		System.out.println("Start Time: "+ AlchemistGlobal.timeStart);
		bankTask = new BankTaskAlchemy();
		alchemyTask = new AlchemyTask();
		provide(bankTask);
		provide(alchemyTask);
		return true;
	}
	
	@Override
	public void onStop() {
		alchemyTask.shouldStop = true;
		revoke(alchemyTask);
		revoke(bankTask);
		terminated(alchemyTask);
		terminated(bankTask);
		AlchemistGlobal.retrieveSessionStats();
	}
	
	@Override
	public void onRepaint(Graphics2D arg0) {
		// TODO Auto-generated method stub
	}
	
	private void setupOptionPanel() {
		Runnable runner = new Runnable() {
			public void run() {
				System.out.println("In Run()");
				JFrame frame = new JFrame("The Alchemist");
				AlchemistOptionPanel optionPanel = new AlchemistOptionPanel();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(optionPanel);
				frame.setSize(400, 300);
				frame.setVisible(true);
			}
		};
		try {
			EventQueue.invokeAndWait(runner);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
