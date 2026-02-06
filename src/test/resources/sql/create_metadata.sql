-- Insert metadata - only required fields
INSERT INTO metadata.entity (kind,name,created,last_modified,status)
VALUES
  ( -- folder
      'directory',
      'test directory',
      '2004-10-19T10:23:54.000Z',
      '2012-01-23T16:11:03.000Z',
      'active'
  ),
  ( -- file
      'file',
      'test file.png',
      '2004-10-19T10:23:54.000Z',
      '2012-01-23T16:11:03.000Z',
      'active'
  );