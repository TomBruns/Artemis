package team2102.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import team2102.robot.LoggingCommandBase;

/**
 *
 */
public class SelectGearFeederCommand extends LoggingCommandBase {

	private Command m_cmd;

	public SelectGearFeederCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	@Override
	protected void initialize() {
		super.initialize();

		switch (DriverStation.getInstance().getAlliance()) {
		case Red:
			m_cmd = new SideGearPlacerFeederRedCommand();
			break;

		case Blue:
			m_cmd = new SideGearPlacerFeederBlueCommand();
			break;

		case Invalid:
			throw new IllegalStateException("Alliance is invalid");
		}

		m_cmd.start();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return !m_cmd.isRunning();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		super.end();

		m_cmd.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		super.interrupted();
		end();
	}
}
