package org.jarego.nbplugin.jswingfixes;

import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {
	@Override
	public void restored() {
		ExtendedLookAndFeel.applyLookAndFeelExtended();
	}
}
