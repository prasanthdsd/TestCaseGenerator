package com.dsdp.ut;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Random;

import org.cornutum.tcases.SystemTestDef;
import org.cornutum.tcases.openapi.io.TcasesOpenApiIO;
import org.cornutum.tcases.openapi.resolver.RequestCases;
import org.cornutum.tcases.openapi.resolver.RequestTestDef;
import org.cornutum.tcases.openapi.resolver.ResolverContext;
import org.cornutum.tcases.openapi.testwriter.JavaTestTarget;
import org.cornutum.tcases.openapi.testwriter.TestCaseWriter;
import org.cornutum.tcases.openapi.testwriter.TestSource;
import org.cornutum.tcases.openapi.testwriter.TestWriter;

import com.dsdp.ut.writer.PSTestCaseWriter;
import com.dsdp.ut.writer.PSTestWriter;
import com.dsdp.ut.writer.UTTestCaseWriter;
import com.dsdp.ut.writer.UTTestWriter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UnitTestCaseGenerator {

    private final String outDir;
    private final String name;
    private final File openApiJson;
    private final String testPackage;

    public void writePostmanCollection() throws FileNotFoundException {
        SystemTestDef inputDef = TcasesOpenApiIO.getRequestTests(this.getOpenApiJson());

        RequestTestDef requestTestDefs = RequestCases.getRequestCases(inputDef, ResolverContext
                .builder(new Random())
                .build());

        TestSource source =
                TestSource.from(requestTestDefs)
                        .paths()
                        .operations()
                        .build();
        JavaTestTarget target =
                JavaTestTarget.builder()
                        .named(this.getName())
                        .inPackage(this.getTestPackage())
                        .build();

        target.setDir(new File(this.getOutDir()));
        TestCaseWriter testCaseWriter = new PSTestCaseWriter();
        TestWriter<TestSource, JavaTestTarget> testWriter = new PSTestWriter(testCaseWriter);
        testWriter.writeTest(source, target);
    }


    public void writeTestCases() throws FileNotFoundException {
        SystemTestDef inputDef = TcasesOpenApiIO.getRequestTests(this.getOpenApiJson());
        RequestTestDef requestTestDefs = RequestCases.getRequestCases(inputDef, ResolverContext

                .builder(new Random())
                .build());

        TestSource source =
                TestSource.from(requestTestDefs)
                        .paths()
                        .operations()
                        .build();

        JavaTestTarget target =
                JavaTestTarget.builder()
                        .named(this.getName())
                        .inDir(new File(Paths.get(this.getOutDir(), this.getTestPackage().split("[.]")).toString()))
                        .inPackage(this.getTestPackage())
                        .build();

        UTTestCaseWriter testCaseWriter = new UTTestCaseWriter();
        testCaseWriter.setValidateResponses(false);
        TestWriter<TestSource, JavaTestTarget> testWriter = new UTTestWriter(testCaseWriter);
        testWriter.writeTest(source, target);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Swagger YAML directory
        String dir = "C:\\Users\\91837\\Downloads\\unit-test-generator\\src\\main\\resources\\schema";
        //Junit Test Case Directory
        String outDir = "C:\\Users\\91837\\Downloads\\unit-test-generator\\src\\test\\java";

        UnitTestCaseGenerator.builder().outDir(outDir)
                .testPackage("com.dsdp.ut.ps.model")
                .openApiJson(new File(dir, "sample-api.yaml"))
                .build().writeTestCases();

        UnitTestCaseGenerator.builder().outDir(outDir)
                .testPackage("com.dsdp.ut.ps.model")
                .openApiJson(new File(dir, "sample-api.yaml"))
                .build().writePostmanCollection();

    }

}