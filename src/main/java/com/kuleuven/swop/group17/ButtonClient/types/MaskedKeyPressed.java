package com.kuleuven.swop.group17.ButtonClient.types;

import java.util.TimerTask;

import com.kuleuven.swop.group17.ButtonClient.guiLayer.MaskedKeyBag;


	public class MaskedKeyPressed extends TimerTask {
		private MaskedKeyBag bag;
		private Boolean resetBoth;
		

		/**
		 * 
		 */
		public MaskedKeyPressed(MaskedKeyBag bag,Boolean resetBoth) {
			this.bag=bag;
			this.resetBoth = resetBoth;
		}

		@Override
		public void run() {
			if(resetBoth) {
			bag.setShift(false);
			bag.setCtrl(false);
			}
			else {
				bag.setCtrl(false);
			}
		}

}
