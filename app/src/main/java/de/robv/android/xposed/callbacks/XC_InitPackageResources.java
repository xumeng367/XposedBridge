package de.robv.android.xposed.callbacks;

import android.content.res.XResources;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedBridge.CopyOnWriteSortedSet;

public abstract class XC_InitPackageResources extends XCallback {
	@SuppressWarnings("deprecation")
	public XC_InitPackageResources() {
		super();
	}

	public XC_InitPackageResources(int priority) {
		super(priority);
	}

	public static final class InitPackageResourcesParam extends XCallback.Param {
		/** @hide */
		public InitPackageResourcesParam(CopyOnWriteSortedSet<XC_InitPackageResources> callbacks) {
			super(callbacks);
		}
		/** The name of the package for which resources are being loaded */
		public String packageName;
		/** Reference to the resources that can be used for calls to {@link XResources#setReplacement} */
		public XResources res;
	}

	/** @hide */
	@Override
	protected void call(Param param) throws Throwable {
		if (param instanceof InitPackageResourcesParam)
			handleInitPackageResources((InitPackageResourcesParam) param);
	}

	public abstract void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable;

	public class Unhook implements IXUnhook {
		/** @hide */
		public Unhook() {}

		public XC_InitPackageResources getCallback() {
			return XC_InitPackageResources.this;
		}

		@Override
		public void unhook() {
			XposedBridge.unhookInitPackageResources(XC_InitPackageResources.this);
		}
	}
}
