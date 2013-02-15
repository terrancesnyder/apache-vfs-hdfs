package org.pentaho.hdfs.vfs;

import org.apache.commons.vfs2.provider.URLFileNameParser;


/**
 * Parses MapR FileSystem URIs. This only differs from {@link URLFileNameParser} in that it allows empty host names.
 *
 * @author Jordan Ganoff (jganoff@pentaho.com)
 */
public class MapRFileNameParser extends URLFileNameParser {
  public static final int DEFAULT_PORT = -1;
  public static final String EMPTY_HOSTNAME = "";

  public MapRFileNameParser() {
    super(DEFAULT_PORT);
  }

  @Override
  protected String extractHostName(StringBuilder name) {
    final String hostname = super.extractHostName(name);
    // Trick the URLFileNameParser into thinking we have a hostname so we don't have to refactor it.
    return hostname == null ? EMPTY_HOSTNAME : hostname;
  }
}
