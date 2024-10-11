package org.talend.designer.codegen.translators.common;

import java.util.Vector;
import java.util.List;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;

public class Header_additionalJava
{
  protected static String nl;
  public static synchronized Header_additionalJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_additionalJava result = new Header_additionalJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    static String filterObject(Object input) {" + NL + "        if (input == null) {" + NL + "            return \"null\";" + NL + "        }" + NL + "        if (input.getClass().isArray()) {" + NL + "            return \"\\\"? (array)\\\"\";" + NL + "        }" + NL + "        if (input instanceof Iterable) {" + NL + "            return \"\\\"? (collection)\\\"\";" + NL + "        }" + NL + "        if (input instanceof java.util.Map) {" + NL + "            return \"\\\"? (map)\\\"\";" + NL + "        }" + NL + "        if (input instanceof java.util.Date) {" + NL + "            return \"\\\"\" + java.time.format.DateTimeFormatter.ofPattern(\"yyyy-MM-dd'T'HH:mm:ss.SSSZ\").withZone(java.time.ZoneOffset.UTC).format(((java.util.Date) input).toInstant()) + \"\\\"\";" + NL + "        }" + NL + "        if (input instanceof CharSequence" + NL + "                || input instanceof Character) {" + NL + "            return routines.system.JSONObject.quote(input.toString());" + NL + "        }" + NL + "        if (input instanceof Enum" + NL + "                || input instanceof Class" + NL + "                || input instanceof Throwable) {" + NL + "            return \"\\\"\" + input.toString() + \"\\\"\";" + NL + "        }" + NL + "        if (input instanceof Number" + NL + "                || input instanceof Boolean) {" + NL + "            return input.toString();" + NL + "        }" + NL + "        return \"\\\"? (java object)\\\"\";" + NL + "    }" + NL + "    static class RejectWriter {" + NL + "        java.io.Writer w;" + NL + "        boolean empty = true;" + NL + "        boolean comma;" + NL + "        RejectWriter() {" + NL + "            String rejectFile = System.getProperty(\"ipaas.reject.file\");" + NL + "            if (null != rejectFile) {" + NL + "                try {" + NL + "                    w = java.nio.file.Files.newBufferedWriter(java.nio.file.Paths.get(rejectFile));" + NL + "                } catch (java.io.IOException e) {" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        void begin() throws java.io.IOException {" + NL + "            if (null != w) {" + NL + "                if (!empty) {" + NL + "                    w.write(\"\\n,\");" + NL + "                }" + NL + "                empty = false;" + NL + "                comma = false;" + NL + "                w.write('{');" + NL + "            }" + NL + "        }" + NL + "        void write(String key, Object value) throws java.io.IOException {" + NL + "            if (null != w) {" + NL + "                if (comma) {" + NL + "                    w.write(',');" + NL + "                }" + NL + "                comma = true;" + NL + "                w.write('\"'); w.write(key); w.write('\"'); w.write(':'); w.write(filterObject(value));" + NL + "            }" + NL + "        }" + NL + "        void end() throws java.io.IOException {" + NL + "            if (null != w) {" + NL + "                w.write('}');" + NL + "                w.flush();" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "    RejectWriter rejectWriter = new RejectWriter();";
  protected final String TEXT_2 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
Vector v = (Vector) codeGenArgument.getArgument();
IProcess process = (IProcess) v.get(0);

boolean hasReject = !process.getNodesOfType("tJobReject").isEmpty();
if (hasReject) {

    stringBuffer.append(TEXT_1);
     } // end if (hasReject) 
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
