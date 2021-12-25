#!/bin/sh
# Set library path for SNAP packages (Ubuntu support)
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/snap/vlc/current/usr/lib

JAVA_OPTS="-Xms1024m -Xmx1024m"

if [ "${XDG_SESSION_TYPE-}" = "wayland" ]; then
	# GDK_BACKEND=x11 required for GUI to start on wayland
	GDK_BACKEND="x11"
	export GDK_BACKEND
	# Disable X initialization in VLCJ to avoid SIGSEGV on wayland
	# Hopefully this is no longer required with the VLCJ update
	# JAVA_OPTS="${JAVA_OPTS} -DVLCJ_INITX=no"
fi

java ${JAVA_OPTS} \
	-jar GuideMe.jar
