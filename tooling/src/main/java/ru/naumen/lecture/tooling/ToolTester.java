package ru.naumen.lecture.tooling;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.bull.javamelody.Parameter;

public class ToolTester
{
    public static Logger LOG = LogManager.getRootLogger();

    public static void main(String[] args) throws Exception
    {
        ToolTesterUtils.registerMBean();
        final Map<Parameter, String> parameters = new HashMap<>();
        parameters.put(Parameter.STORAGE_DIRECTORY, "/tmp/javamelody");
        parameters.put(Parameter.SAMPLING_SECONDS, "1.0");
        parameters.put(Parameter.MONITORING_PATH, "/m");
        parameters.put(Parameter.MAVEN_REPOSITORIES, "/home/amokhov/.m2/repository");
        EmbeddedServer.start(8080, parameters);
    }
}
