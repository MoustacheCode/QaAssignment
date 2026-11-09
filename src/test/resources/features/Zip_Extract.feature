Feature: Zip file processing

  @positive
  Scenario: Process a ZIP containing PDF files
    Given a ZIP file contains only PDF files
    When the ZIP file is processed
    Then the response status code should be 200
    And the files should be extracted to the output folder

  @positive
  Scenario: Process a ZIP containing JPEG files
    Given a ZIP file contains only JPEG files
    When the ZIP file is processed
    Then the response status code should be 200
    And the files should be extracted to the output folder

  @positive
  Scenario: Process a ZIP containing PDF and JPEG files
    Given a ZIP file contains a PDF and JPEG files
    When the ZIP file is processed
    Then the response status code should be 200
    And the files should be extracted to the output folder

  @negative
  Scenario: Reject a ZIP containing unsupported file type
    Given a ZIP file contains an unsupported file type
    When the ZIP file is processed
    Then the response status code should be 400

  @negative
  Scenario: Reject a ZIP containing supported and unsupported files
    Given a ZIP file contains PDF and JPEG files
    And the ZIP file contains an unsupported file type
    When the ZIP file is processed
    Then the response status code should be 400

  @negative
  Scenario: Reject a corrupt ZIP file
    Given the ZIP file is corrupt
    When the ZIP file is processed
    Then the response status code should be 400

  @negative
  Scenario: Reject a ZIP file that does not exist
    Given the specified ZIP file does not exist
    When the ZIP file is processed
    Then the response status code should be 400