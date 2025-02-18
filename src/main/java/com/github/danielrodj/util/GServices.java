package com.github.danielrodj.util;

import com.github.danielrodj.services.CategoryServiceImplementation;
import com.github.danielrodj.services.PLabelServiceImplementation;
import com.github.danielrodj.services.PhaseServiceImplementation;
import com.github.danielrodj.services.PrinterServiceImplementation;
import com.github.danielrodj.services.RLabelServiceImplementation;
import com.github.danielrodj.services.RequesterServiceImplementation;
import com.github.danielrodj.services.UserServiceImplementation;

public class GServices {
    public static final PLabelServiceImplementation pLabelService = new PLabelServiceImplementation();
    public static final RLabelServiceImplementation rLabelService = new RLabelServiceImplementation();
    public static final RequesterServiceImplementation requesterService = new RequesterServiceImplementation();
    public static final CategoryServiceImplementation categoryService = new CategoryServiceImplementation();
    public static final PrinterServiceImplementation printerService = new PrinterServiceImplementation();
    public static final UserServiceImplementation userService = new UserServiceImplementation();
    public static final PhaseServiceImplementation phaseService = new PhaseServiceImplementation();
}
