# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.12

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2018.2.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2018.2.4\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/FenceHillValley.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/FenceHillValley.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/FenceHillValley.dir/flags.make

CMakeFiles/FenceHillValley.dir/main.cpp.obj: CMakeFiles/FenceHillValley.dir/flags.make
CMakeFiles/FenceHillValley.dir/main.cpp.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/FenceHillValley.dir/main.cpp.obj"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\FenceHillValley.dir\main.cpp.obj -c C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\main.cpp

CMakeFiles/FenceHillValley.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/FenceHillValley.dir/main.cpp.i"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\main.cpp > CMakeFiles\FenceHillValley.dir\main.cpp.i

CMakeFiles/FenceHillValley.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/FenceHillValley.dir/main.cpp.s"
	C:\PROGRA~2\MINGW-~1\I686-8~1.0-P\mingw32\bin\G__~1.EXE $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\main.cpp -o CMakeFiles\FenceHillValley.dir\main.cpp.s

# Object files for target FenceHillValley
FenceHillValley_OBJECTS = \
"CMakeFiles/FenceHillValley.dir/main.cpp.obj"

# External object files for target FenceHillValley
FenceHillValley_EXTERNAL_OBJECTS =

FenceHillValley.exe: CMakeFiles/FenceHillValley.dir/main.cpp.obj
FenceHillValley.exe: CMakeFiles/FenceHillValley.dir/build.make
FenceHillValley.exe: CMakeFiles/FenceHillValley.dir/linklibs.rsp
FenceHillValley.exe: CMakeFiles/FenceHillValley.dir/objects1.rsp
FenceHillValley.exe: CMakeFiles/FenceHillValley.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable FenceHillValley.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\FenceHillValley.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/FenceHillValley.dir/build: FenceHillValley.exe

.PHONY : CMakeFiles/FenceHillValley.dir/build

CMakeFiles/FenceHillValley.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\FenceHillValley.dir\cmake_clean.cmake
.PHONY : CMakeFiles/FenceHillValley.dir/clean

CMakeFiles/FenceHillValley.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug C:\Users\Joshua\Documents\CourseWork\OPERATINGSYSTEMS\FenceHillValley\cmake-build-debug\CMakeFiles\FenceHillValley.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/FenceHillValley.dir/depend

