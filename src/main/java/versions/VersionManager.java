package versions;

import java.util.Arrays;

public class VersionManager {

	private static final String defaultVersion = "0.0.1";

	private int major;
	private int minor;
	private int patch;
	
	private VersionManager previous;

	public VersionManager() {
		this(defaultVersion);
	}

	public VersionManager(final String version) {
		final String[] split = version.split("\\.");
		if (split.length > 0) {
			major = Integer.parseInt(split[0]);
		}
		if (split.length > 1) {
			minor = Integer.parseInt(split[1]);
		}
		if (split.length > 2) {
			patch = Integer.parseInt(split[2]);
		}
	}

	public VersionManager(final int major, final int minor, final int patch, final VersionManager previous) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.previous = previous;
	}

	public String release() {
		return major + "." + minor + "." + patch;
	}

	public VersionManager major() {
		return new VersionManager(major + 1, 0, 0, this);
	}

	public VersionManager minor() {
		return new VersionManager(major, minor + 1, 0, this);
	}

	public VersionManager patch() {
		return new VersionManager(major, minor, patch + 1, this);
	}

	public VersionManager rollback() {
		if (previous == null) {
			throw new IllegalStateException("Error occured while parsing version!");
		}
		return previous;
	}
}