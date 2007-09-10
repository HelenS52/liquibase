package liquibase.change;

import liquibase.database.OracleDatabase;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Tests for {@link DropSequenceChange}
 */
public class DropSequenceChangeTest extends AbstractChangeTest {

    private DropSequenceChange change;

    @Before
    public void setUp() throws Exception {
        change = new DropSequenceChange();
        change.setSequenceName("SEQ_NAME");
    }

    @Test
    public void getRefactoringName() throws Exception {
        assertEquals("Drop Sequence", new DropSequenceChange().getChangeName());
    }

    @Test
    public void generateStatement() throws Exception {
        OracleDatabase database = new OracleDatabase();
        assertEquals("DROP SEQUENCE SEQ_NAME", change.generateStatements(database)[0].getSqlStatement(database));
    }

    @Test
    public void getConfirmationMessage() throws Exception {
        assertEquals("Sequence SEQ_NAME dropped", change.getConfirmationMessage());
    }

    @Test
    public void createNode() throws Exception {
        Element element = change.createNode(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        assertEquals("dropSequence", element.getTagName());
        assertEquals("SEQ_NAME", element.getAttribute("sequenceName"));
    }
}