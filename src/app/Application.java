package app;

import java.io.File;

public class Application 
{
    private static final File PROFILES = new File("./res/profiles/");
    private static final File DEPARTMENT_CS = new File("./res/departments/CS");


    public boolean createProfile() 
    { 
        return false;
    }

    public boolean doesProfileExists()
    {
        return false;
    }

    public Profile[] listProfiles()
    {
        return null;
    }
}