package googlecalander.reminder;

public class SetReminderCommand implements Command {
    private Reminder reminder;

    public SetReminderCommand(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    public void execute() {
        reminder.setReminder();
    }
}
