package com.dsdp.ut.writer;

import static org.cornutum.tcases.openapi.testwriter.TestWriterUtils.getPathParameterValue;
import static org.cornutum.tcases.openapi.testwriter.TestWriterUtils.stringLiteral;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.cornutum.tcases.io.IndentedWriter;
import org.cornutum.tcases.openapi.resolver.ParamData;
import org.cornutum.tcases.openapi.resolver.RequestCase;
import org.cornutum.tcases.openapi.restassured.RestAssuredTestCaseWriter;
import org.cornutum.tcases.openapi.testwriter.TestCaseWriter;





public class UTTestCaseWriter extends RestAssuredTestCaseWriter {
	
	public UTTestCaseWriter()
    {
		super();
    }
	
	@Override
	public void writeDependencies( String testName, IndentedWriter targetWriter)
    {
		targetWriter.println();
	    if( getDepends().validateResponses())
	      {
	      targetWriter.println( "import java.util.Map;");
	      targetWriter.println( "import static java.util.stream.Collectors.toMap;");
	      targetWriter.println();
	      targetWriter.println( "import io.restassured.http.Header;");
	      targetWriter.println( "import io.restassured.response.Response;");
	      targetWriter.println();
	      targetWriter.println( "import org.cornutum.tcases.openapi.test.ResponseValidator;");
	      targetWriter.println();
	      }
	    if( getDepends().dependsMultipart())
	      {
	      targetWriter.println( "import io.restassured.builder.MultiPartSpecBuilder;");
	      targetWriter.println();
	      }
	    targetWriter.println( "import org.hamcrest.Matcher;");
	    targetWriter.println( "import static io.restassured.RestAssured.*;");
	    targetWriter.println( "import static org.hamcrest.Matchers.*;");
	    targetWriter.println("import org.springframework.beans.factory.annotation.Autowired;\r\n"
	    		+ "import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;\r\n"
	    		+ "import org.springframework.boot.test.mock.mockito.MockBean;\r\n"
	    		+ "import org.springframework.http.MediaType;\r\n"
	    		+ "import org.springframework.mock.web.MockHttpServletResponse;\r\n"
	    		+ "import org.springframework.test.web.servlet.MockMvc;\r\n"
	    		+ "import org.springframework.test.web.servlet.MvcResult;\r\n"
	    		+ "import org.springframework.test.web.servlet.ResultActions;\r\n"
	    		+ "import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;\r\n"
	    		+ "import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;");
	    targetWriter.println();
	    targetWriter.println();
	    targetWriter.println("//Add the controller class Name in the space of name");
	    targetWriter.println("@WebMvcTest(value = 'name'.class)");
    
    }
	
	@Override
	public void writeTestCase( String testName, URI testServer, RequestCase requestCase, IndentedWriter targetWriter)
	{
		
		if(requestCase.getParams().toString().contains("QUERY"))
		{
			targetWriter.println( "MockHttpServletRequestBuilder req = MockMvcRequestBuilders."+requestCase.getOperation().toLowerCase()+"(\""+requestCase.getPath()+"\")");
			for( ParamData param : requestCase.getParams())
		      {
				writeQueryParam( testName, param, targetWriter);
		      }
		}
		else if(requestCase.getParams().toString().contains("PATH"))
		{
			targetWriter.println( "MockHttpServletRequestBuilder req = MockMvcRequestBuilders."+requestCase.getOperation().toLowerCase()+"(\""+requestCase.getPath()+"\",");
			for( ParamData param : requestCase.getParams())
		      {
				writePathParam( testName, param, targetWriter);
				targetWriter.print(")");
		      }
		}
		targetWriter.print(";");
		targetWriter.println("try {\r\n"
				+ "ResultActions perform = mockMvc.perform(req);\r\n"
			    + "MvcResult mvcResult = perform.andReturn();\n"
				+ "MockHttpServletResponse response = mvcResult.getResponse();\n"
				+ "int statusCode = response.getStatus();");
		targetWriter.println("assertEquals(");
		writeExpectResponse(testName, requestCase, targetWriter);
		targetWriter.println(", statusCode);");
		targetWriter.println("}\r\n"
				+ "catch (Exception e) {\r\n"
				+ "	e.printStackTrace();\r\n"
				+ "}");
		 
	}
	
	
	@Override
	 protected void writePathParam( String testName, ParamData param, IndentedWriter targetWriter)
    {
    targetWriter.println( String.format( "%s", stringLiteral( getPathParameterValue( param))));
    }
	
	@Override
	 protected void writeExpectResponse( String testName, RequestCase requestCase, IndentedWriter targetWriter)
	    {
	    if( requestCase.isFailure())
	      {
	      //targetWriter.print( String.format( "// %s", requestCase.getInvalidInput()));
	      targetWriter.print( String.format( "%s", requestCase.isAuthFailure()? "401" : "400"));
	      }
	    else
	      {
	      targetWriter.print( "200");
	      }
	    }

}
