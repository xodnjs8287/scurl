import com.beust.jcommander.Parameter;

public class CommandLine {

    @Parameter(
            required = true

    )
    private String url;


    @Parameter(
            names = "-v"

    )
    private boolean verbose;


    //    @Parameter(
//            names = {"-V", "-v"}
//    )
//    private String header;
//
//    public String getHeader() {
//        System.out.println(header + "header");
//        return header;
//    }

    @Parameter(
            names = "-H",
            arity = 1
    )
    private String line;

    @Parameter(
            names = "-D",
            arity = 1
    )
    private String data;

    @Parameter(
            names = "-X",
            arity = 1
    )
    private String command;


    @Parameter(
            names = "-L"

    )
    private boolean isNextResponse;

    @Parameter(
            names = "-F",
            arity = 1
    )
    private String nameContent;


    public String getUrl() {
        return url;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public String getLine() {
        return line;
    }

    public String getData() {
        return data;
    }

    public String getCommand() {

        return command;
    }

    public boolean isNextResponse() {

        return isNextResponse;
    }

    public String getNameContent() {


        return nameContent;
    }
}
