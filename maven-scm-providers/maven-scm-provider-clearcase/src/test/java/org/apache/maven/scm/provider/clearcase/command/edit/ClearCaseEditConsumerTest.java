package org.apache.maven.scm.provider.clearcase.command.edit;

import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import org.apache.maven.scm.ScmTestCase;
import org.apache.maven.scm.log.DefaultLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * 
 */
public class ClearCaseEditConsumerTest extends ScmTestCase
{
    public void testConsumer() throws IOException
    {
        InputStream inputStream = getResourceAsStream( "/clearcase/edit/edit.txt" );

        BufferedReader in = new BufferedReader( new InputStreamReader( inputStream ) );

        String s = in.readLine();

        ClearCaseEditConsumer consumer = new ClearCaseEditConsumer( new DefaultLog() );

        while (s != null)
        {
            consumer.consumeLine( s );

            s = in.readLine();
        }

        Collection entries = consumer.getEditFiles();

        assertEquals( "Wrong number of entries returned", 1, entries.size() );

        ScmFile scmFile = (ScmFile)entries.iterator().next();
        assertEquals( "test.java", scmFile.getPath() );
        assertEquals( ScmFileStatus.UNKNOWN, scmFile.getStatus() );
    }
}