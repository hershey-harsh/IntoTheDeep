import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;

import java.util.Set;

public class Intake extends Command {

    private final Set<Subsystem> subsystems;
    private final boolean interruptible = true;

    public Intake(Set<Subsystem> subsystems) {
        this.subsystems = subsystems;
    }

    @Override
    public boolean isDone() {
        return false; // Whether or not the command is done
    }

    @Override
    public void start() {
        // Executed when the command begins
    }

    @Override
    public void update() {
        // Executed on every update of the command
    }

    @Override
    public void stop(boolean interrupted) {
        // Executed when the command ends
    }
}