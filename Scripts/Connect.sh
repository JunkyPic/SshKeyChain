#!/usr/bin/env bash

osascript <<END
if application "Terminal" is running then
    tell application "Terminal"
        # do script without "in window" will open a new window
        do script "$command"
        activate
    end tell
else
    tell application "Terminal"
        # window 1 is guaranteed to be recently opened window
        do script "$command" in window 1
        activate
    end tell
end if
END
