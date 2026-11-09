Feature: ZIP file processing

  Background:
    Given the ZIP processing folders exist
    And the ZIP processing folders are empty


  @positive @api
  Scenario: Successfully process a ZIP containing a PDF file
    Given a ZIP file named "pdfFile.zip" contains:
      | fileName        | type |
      | testFile.pdf    | PDF  |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And "pdfFile.zip" no longer exists in the input folder
    And "pdfFile.zip" does not exist in the error folder
    And a processed ZIP has been created
    And Base64 output has been created


  @positive @api
  Scenario: Successfully process a ZIP containing a JPEG file
    Given a ZIP file named "jpegFile.zip" contains:
      | fileName        | type |
      | testPicture.jpg | JPEG |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And "jpegFile.zip" no longer exists in the input folder
    And "jpegFile.zip" does not exist in the error folder
    And a processed ZIP has been created
    And Base64 output has been created


  @positive @api
  Scenario: Successfully process a ZIP containing multiple PDF files
    Given a ZIP file named "multiplePdfFiles.zip" contains:
      | fileName         | type |
      | testFile.pdf     | PDF  |
      | testFile2.pdf    | PDF  |
      | testFile3.pdf    | PDF  |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And "multiplePdfFiles.zip" no longer exists in the input folder
    And "multiplePdfFiles.zip" does not exist in the error folder
    And a processed ZIP has been created
    And Base64 output has been created


  @positive @api
  Scenario: Successfully process a ZIP containing multiple JPEG files
    Given a ZIP file named "multipleJpegFiles.zip" contains:
      | fileName         | type |
      | testPicture.jpg  | JPEG |
      | testPicture2.jpg | JPEG |
      | testPicture3.jpg | JPEG |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And "multipleJpegFiles.zip" no longer exists in the input folder
    And "multipleJpegFiles.zip" does not exist in the error folder
    And a processed ZIP has been created
    And Base64 output has been created


  @positive @api
  Scenario: Successfully process a ZIP containing PDF and JPEG files
    Given a ZIP file named "supportedFiles.zip" contains:
      | fileName            | type |
      | testFile.pdf        | PDF  |
      | testPicture.jpg     | JPEG |
      | testDocument.pdf    | PDF  |
      | testImage.jpeg      | JPEG |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And "supportedFiles.zip" no longer exists in the input folder
    And "supportedFiles.zip" does not exist in the error folder
    And a processed ZIP has been created
    And Base64 output has been created


  @negative @api
  Scenario: Reject a ZIP containing an unsupported TXT file
    Given a ZIP file named "textFile.zip" contains:
      | fileName            | type |
      | testFile.pdf        | PDF  |
      | testNotes.txt       | TEXT |
    When ZIP file processing is requested
    Then the API response code is 400
    And the response status is "FAILED"
    And the response message is "Unsupported file type found"
    And the response unsupported file name is "notes.txt"
    And "textFile.zip" exists in the error folder
    And "textFile.zip" no longer exists in the input folder
    And no processed ZIP has been created
    And no Base64 output has been created


  @negative @api
  Scenario: Reject a ZIP containing an unsupported PNG file
    Given a ZIP file named "pngFile.zip" contains:
      | fileName        | type |
      | testPicture.png | PNG  |
    When ZIP file processing is requested
    Then the API response code is 400
    And the response status is "FAILED"
    And the response message is "Unsupported file type found"
    And the response unsupported file name is "picture.png"
    And "pngFile.zip" exists in the error folder
    And "pngFile.zip" no longer exists in the input folder
    And no processed ZIP has been created
    And no Base64 output has been created


  @negative @api
  Scenario: Reject a ZIP containing supported and unsupported files
    Given a ZIP file named "mixedFiles.zip" contains:
      | fileName            | type |
      | testFile.pdf        | PDF  |
      | testPicture.jpg     | JPEG |
      | testNotes.txt       | TEXT |
    When ZIP file processing is requested
    Then the API response code is 400
    And the response status is "FAILED"
    And the response message is "Unsupported file type found"
    And the response unsupported file name is "notes.txt"
    And "mixedFiles.zip" exists in the error folder
    And "mixedFiles.zip" no longer exists in the input folder
    And no processed ZIP has been created
    And no Base64 output has been created


  @negative @api
  Scenario: Reject an empty ZIP file
    Given an empty ZIP file named "emptyFile.zip" is created
    When ZIP file processing is requested
    Then the API response code is 400
    And the response status is "FAILED"
    And "emptyFile.zip" exists in the error folder
    And "emptyFile.zip" no longer exists in the input folder
    And no processed ZIP has been created
    And no Base64 output has been created
