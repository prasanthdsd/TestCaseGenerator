package com.dsdp.ut.writer;

import org.cornutum.tcases.io.IndentedWriter;
import org.cornutum.tcases.openapi.resolver.RequestCase;
import org.cornutum.tcases.openapi.testwriter.AnnotatedJavaTestWriter;
import org.cornutum.tcases.openapi.testwriter.JUnitTestWriter;
import org.cornutum.tcases.openapi.testwriter.JavaTestTarget;
import org.cornutum.tcases.openapi.testwriter.TestCaseWriter;

public class UTTestWriter extends JUnitTestWriter{
	
	public UTTestWriter( TestCaseWriter testCaseWriter)
    {
		super( testCaseWriter);
    }
	
	@Override
	protected void writeTestAnnotationDependencies( JavaTestTarget target, String testName, IndentedWriter targetWriter)
    {
		targetWriter.println( "import static org.junit.jupiter.api.Assertions.assertEquals;\r\n"
				+ "import org.junit.jupiter.api.Test;");
    }

	

}
