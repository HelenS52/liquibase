package liquibase.change;

import liquibase.database.Database;
import liquibase.database.sql.SqlStatement;
import liquibase.database.sql.RawSqlStatement;
import liquibase.database.structure.DatabaseObject;
import liquibase.database.structure.View;
import liquibase.exception.UnsupportedChangeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Drops an existing view.
 */
public class DropViewChange extends AbstractChange {
    private String viewName;

    public DropViewChange() {
        super("dropView", "Drop View");
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public SqlStatement[] generateStatements(Database database) throws UnsupportedChangeException {
        return new SqlStatement[]{
                new RawSqlStatement("DROP VIEW " + viewName),
        };
    }

    public String getConfirmationMessage() {
        return "View Dropped";
    }

    public Element createNode(Document currentChangeLogFileDOM) {
        Element element = currentChangeLogFileDOM.createElement("dropView");
        element.setAttribute("viewName", getViewName());

        return element;
    }

    public Set<DatabaseObject> getAffectedDatabaseObjects() {
        View dbObject = new View();
        dbObject.setName(viewName);

        return new HashSet<DatabaseObject>(Arrays.asList(dbObject));
    }

}
