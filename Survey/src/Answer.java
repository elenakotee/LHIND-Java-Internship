public enum Answer {
	DISAGREE {
		@Override
		public String toString() {
			return "Disagree";
		}
	},
	SLIGHTLY_DISAGREE {
		@Override
		public String toString() {
			return "Slightly Disagree";
		}
	},
	SLIGHTLY_AGREE {
		@Override
		public String toString() {
			return "Slightly Agree";
		}
	},
	AGREE {
		@Override
		public String toString() {
			return "Agree";
		}
	},
	SKIP {
		@Override
		public String toString() {
			return "Skip";
		}
	},
}
