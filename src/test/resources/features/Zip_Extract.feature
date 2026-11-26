Feature: ZIP file processing

  Background:
    Given the ZIP processing folders exist
    And the ZIP processing folders are empty


#  @debug @positive @api
#  Scenario: Successfully process a ZIP containing a PDF file
#    Given a ZIP file named "pdfFile.zip" contains:
#      | fileName        | type |
#      | testFile.pdf    | PDF  |
#    When ZIP file processing is requested
#    Then the API response code is 200
#    And the response status is "SUCCESS"
#    And the response source file is "pdfFile.zip"
#    And the response processed file is "pdfFile_processed.zip"
#    And the response encoded file is "pdfFile_processed.zip.txt"
#    And "pdfFile.zip" no longer exists in the input folder
#    And "pdfFile.zip" exists in the processed folder
#    And "pdfFile.zip" does not exist in the error folder
#    And the response processed file exists in the processed folder
#    And the response encoded file exists in the encoded folder
#
#
#  @positive @api
#  Scenario: Successfully process a ZIP containing a JPEG file
#    Given a ZIP file named "jpegFile.zip" contains:
#      | fileName        | type |
#      | testPicture.jpg | JPEG |
#    When ZIP file processing is requested
#    Then the API response code is 200
#    And the response status is "SUCCESS"
#    And "jpegFile.zip" no longer exists in the input folder
#    And "jpegFile.zip" exists in the processed folder
#    And "jpegFile.zip" does not exist in the error folder
#    And the response processed file exists in the processed folder
#    And the response encoded file exists in the encoded folder

  # SCENARIO OUTLINE REPLACES THE ABOVE SCENARIOS
  @positive @api
    Scenario Outline: Successfully process a ZIP containing one supported file
    Given a ZIP file named "<zipFile>" contains:
    | fileName   | type       |
    | <fileName> | <fileType> |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And the response source file is "<zipFile>"
    And the response processed file is "<processedFile>"
    And the response encoded file is "<encodedFile>"
    And "<zipFile>" no longer exists in the input folder
    And "<zipFile>" exists in the processed folder
    And "<zipFile>" does not exist in the error folder
    And the response processed file exists in the processed folder
    And the response encoded file exists in the encoded folder

    Examples:
    | zipFile      | fileName         | fileType  | processedFile          | encodedFile                |
    | pdfFile.zip  | testFile.pdf     | PDF       | pdfFile_processed.zip  | pdfFile_processed.zip.txt  |
    | jpegFile.zip | testPicture.jpeg | JPEG      | jpegFile_processed.zip | jpegFile_processed.zip.txt |


  @positive @api
    Scenario Outline: Successfully process a ZIP containing multiple supported files
    Given a ZIP file named "<multipleZipFile>" contains:
    | fileName    | type           |
    | <fileName1> | <fileType>     |
    | <fileName2> | <fileType>     |
    | <fileName3> | <fileType>     |
    When ZIP file processing is requested
    Then the API response code is 200
    And the response status is "SUCCESS"
    And the response source file is "<multipleZipFile>"
    And the response processed file is "<processedFile>"
    And the response encoded file is "<encodedFile>"
    And "<multipleZipFile>" no longer exists in the input folder
    And "<multipleZipFile>" exists in the processed folder
    And "<multipleZipFile>" does not exist in the error folder
    And the response processed file exists in the processed folder
    And the response encoded file exists in the encoded folder

    Examples:
      | multipleZipFile       | fileName1     |  fileName2     |  fileName3     | fileType   | processedFile                   | encodedFile                         |
      | multiplePdfFiles.zip  | testFile.pdf  | testFile2.pdf  | testFile3.pdf  | PDF        | multiplePdfFiles_processed.zip  | multiplePdfFiles_processed.zip.txt  |
      | multipleJpegFiles.zip | testFile.jpg  | testFile2.jpg  | testFile3.jpg  | JPEG       | multipleJpegFiles_processed.zip | multipleJpegFiles_processed.zip.txt |


    #SCENARIO OUTLINE ABOVE REPLACES THE FOLLOWING SCENARIOS
#  @positive @api
#  Scenario: Successfully process a ZIP containing multiple PDF files
#    Given a ZIP file named "multiplePdfFiles.zip" contains:
#      | fileName         | type |
#      | testFile.pdf     | PDF  |
#      | testFile2.pdf    | PDF  |
#      | testFile3.pdf    | PDF  |
#    When ZIP file processing is requested
#    Then the API response code is 200
#    And the response status is "SUCCESS"
#    And the response source file is "multiplePdfFiles.zip"
#    And the response processed file is "multiplePdfFiles_processed.zip"
#    And the response encoded file is "multiplePdfFiles_processed.zip.txt"
#    And "multiplePdfFiles.zip" no longer exists in the input folder
#    And "multiplePdfFiles.zip" exists in the processed folder
#    And "multiplePdfFiles.zip" does not exist in the error folder
#    And the response processed file exists in the processed folder
#    And the response encoded file exists in the encoded folder
#
#
#  @positive @api
#  Scenario: Successfully process a ZIP containing multiple JPEG files
#    Given a ZIP file named "multipleJpegFiles.zip" contains:
#      | fileName         | type |
#      | testPicture.jpg  | JPEG |
#      | testPicture2.jpg | JPEG |
#      | testPicture3.jpg | JPEG |
#    When ZIP file processing is requested
#    Then the API response code is 200
#    And the response status is "SUCCESS"
#    And the response source file is "multipleJpegFiles.zip"
#    And the response processed file is "multipleJpegFiles_processed.zip"
#    And the response encoded file is "multipleJpegFiles_processed.zip.txt"
#    And "multipleJpegFiles.zip" no longer exists in the input folder
#    And "multipleJpegFiles.zip" exists in the processed folder
#    And "multipleJpegFiles.zip" does not exist in the error folder
#    And the response processed file exists in the processed folder
#    And the response encoded file exists in the encoded folder


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
    And the response source file is "supportedFiles.zip"
    And the response processed file is "supportedFiles_processed.zip"
    And the response encoded file is "supportedFiles_processed.zip.txt"
    And "supportedFiles.zip" exists in the processed folder
    And "supportedFiles.zip" does not exist in the error folder
    And "supportedFiles.zip" no longer exists in the input folder
    And the response processed file exists in the processed folder
    And the response encoded file exists in the encoded folder


  @negative @api
  Scenario: Reject a ZIP containing an unsupported TXT file
    Given a ZIP file named "textFile.zip" contains:
      | fileName            | type |
      | testFile.pdf        | PDF  |
      | testNotes.txt       | TEXT |
    When ZIP file processing is requested
    Then the API response code is 400
    And the response status is "FAILED"
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
    And "mixedFiles.zip" exists in the error folder
    And "mixedFiles.zip" no longer exists in the input folder
    And no processed ZIP has been created
    And no Base64 output has been created



  @negative @api
  Scenario: Reject an empty ZIP file
    Given an empty ZIP file named "emptyFile.zip" is created
    When ZIP file processing is requested
    Then the API response code is 400
    And no processed ZIP has been created
    And no Base64 output has been created




