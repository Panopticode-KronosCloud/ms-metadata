-- Insert metadata - only required fields
INSERT INTO metadata.entity (kind,name,created,last_modified,status)
VALUES
  ( -- folder
      'directory',
      'test directory',
      '2004-10-19 10:23:54',
      '2012-01-23 16:11:03',
      'ready'
  ),
  ( -- file
      'file',
      'test file.png',
      '2004-10-19 10:23:54',
      '2012-01-23 16:11:03',
      'staged'
  );