package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public Duke() {}

    public void run() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        Ui ui = new Ui();
        TaskList tl = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser();
        storage.populate(tl);
        ui.showIntro();
        while (true){
            try {
                String str = sc.nextLine();
                Command command = parser.parse(str);
                if (Objects.equals(command, null)) {
                    break;
                }
                command.execute(tl, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }

        }
        ui.showOutro();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
