package DvdCopyWorld.Common;

// ********* THIS CODE IS AUTO PORTED FROM C# TO JAVA USING CODEPORTING.COM TECHNOLOGY *********

import com.codeporting.csharp2java.System.msString;
import com.codeporting.csharp2java.System.IO.MemoryStream;
import com.codeporting.csharp2java.System.IO.SeekOrigin;
import com.codeporting.csharp2java.System.IO.FileInfo;



class UtilityFunctions
{
    public static final long K_KB = 1024;
    public static final long KI_KB = 1024;
    public static final long KI_MB = 1024 * KI_KB;
    public static final long KI_GB = 1024 * KI_MB;

    /**
     * <p>
     * To get size in KB, MB, GB.
     * </p>
     * @return 
     * @param ulSizeInBytes 
     */
    static public String formatSize(/*ulong*/long ulSizeInBytes)
    {
        String csDestination;
        if (ulSizeInBytes > 0)
        {

            if (ulSizeInBytes >= KI_GB)
            {
                double dGBSize = (double)ulSizeInBytes / KI_GB;
                csDestination = msString.format(("{0:F0}"), dGBSize);
                csDestination = com.codeporting.csharp2java.System.msString.concat(csDestination,  " GB");
            }
            else if (ulSizeInBytes >= KI_MB)
            {
                double dMBSize = (double)ulSizeInBytes / KI_MB;
                csDestination = msString.format(("{0:F0}"), dMBSize);
                csDestination = com.codeporting.csharp2java.System.msString.concat(csDestination,  " MB");
            }
            else if (ulSizeInBytes > KI_KB)
            {
                csDestination = msString.format(("{0:F0}"), ulSizeInBytes / KI_KB);
                csDestination = com.codeporting.csharp2java.System.msString.concat(csDestination,  " KB");
            }
            else
            {
                csDestination = "1 KB";
            }
        }
        else
        {
            csDestination = "0 KB";
        }

        return csDestination;
    }

    /**
     * <p>
     * Get type and icon associated with given file/directory
     * </p>
     */
    static public boolean getFileInfo(String path, /*FileAttributes*/int attr, /*ref*/ ImageSource[] imageSource, /*ref*/ String[] type)
    {
        ShellAPI.SHFILEINFO shellInfo = new ShellAPI.SHFILEINFO();
        ShellAPI.SHGFI vFlags =
            ShellAPI.SHGFI.SHGFI_SMALLICON |
            ShellAPI.SHGFI.SHGFI_ICON |
            ShellAPI.SHGFI.SHGFI_USEFILEATTRIBUTES |
            ShellAPI.SHGFI.SHGFI_DISPLAYNAME |
            ShellAPI.SHGFI.SHGFI_TYPENAME;
        <unknown>[] referenceToShellInfo = { shellInfo };
        ShellAPI.SHGetFileInfo(path, (/*uint*/long)attr, /*ref*/ referenceToShellInfo, (/*uint*/long)Marshal.SizeOf(shellInfo), vFlags);
        shellInfo = referenceToShellInfo[0];

        // (1) Type
        type[0] = shellInfo.szTypeName;

        // (2) Image Source.
        imageSource[0] = null;
        if (shellInfo.hIcon != null)
        {
            try
            {
                MemoryStream memStream = new MemoryStream();
                ((com.codeporting.csharp2java.System.Drawing.Bitmap)Icon.FromHandle(shellInfo.hIcon).ToBitmap()).
                    Save(memStream, com.codeporting.csharp2java.System.Drawing.Imaging.ImageFormat.getPng());
                memStream.seek(0, SeekOrigin.Begin);
                PngBitmapDecoder pngBmpDecoder = new PngBitmapDecoder(memStream,
                     BitmapCreateOptions.PreservePixelFormat,
                      BitmapCacheOption.Default);
                imageSource[0] = pngBmpDecoder.Frames[0];
                //User32API.DestroyIcon(shellInfo.hIcon);
            }
            catch (RuntimeException exp)
            {
                //Debug.Assert(false, exp.Message);
            }

            return true;
        }
        return false;
    }

    /**
     * <p>
     * To get the directory size including all files.
     * </p>
     * @return 
     * @param csPath 
     */
    static public /*ulong*/long getDirectorySize(String csPath)
    {
        /*ulong*/long ulSize = 0;
        String[] dirFiles = com.codeporting.csharp2java.System.IO.Directory.getFiles(csPath);

        for (String csFile : dirFiles)
        {
            FileInfo f = new FileInfo(csFile);
            ulSize += (/*ulong*/long)f.getLength();
        }
        return ulSize;
    }

    /**
     * <p>
     * To get the formatted duration string.
     * </p>
     * @return 
     * @param pSeconds 
     */
    static public String formatDurationTime(long pSeconds)
    {
        if (pSeconds <= 0) { return " 00:00 "; }
        if (pSeconds < 60) { return com.codeporting.csharp2java.System.msString.concat(" 00:",  pSeconds,  " "); }

        long sec;
        long min;
        long hour;

        if (pSeconds < 3600)
        {
            min = (pSeconds / 60);
            sec = pSeconds - min * 60;

            return msString.format(" {0:00}:{1:00} ", min, sec);
        }
        long usedSecs = 0;
        hour = pSeconds / 60 / 60;
        usedSecs += hour * 60 * 60;

        min = (pSeconds - usedSecs) / 60;
        usedSecs = usedSecs + min * 60;

        sec = pSeconds - usedSecs;
        return msString.format(" {0:00}:{1:00}:{2:00} ", hour, min, sec);
    }
}

