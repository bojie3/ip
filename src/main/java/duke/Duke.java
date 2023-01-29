package duke;

import java.util.Objects;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * A class for managing tasks
 */
public class Duke{
    private Ui ui;
    private TaskList tl;
    private Storage storage;
    private Parser parser;
    public Duke() {
        this.ui = new Ui();
        this.tl = new TaskList();
        this.storage = new Storage();
        this.parser = new Parser();
        this.storage.populate(this.tl);
    }

    public String getIntro() {
        return this.ui.getIntro();
    }

    String getResponse(String input) {
        try {
            Command command = this.parser.parse(input);
            if (Objects.equals(command, null)) {
                return this.ui.getOutro();
            }
            return command.execute(tl, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

}
