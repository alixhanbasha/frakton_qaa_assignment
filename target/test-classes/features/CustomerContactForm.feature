@Consumer_Contact_Form
@Consumer_Contact_Form:UI
@context:UI

Feature: Consumer Contact Form

  Rule: The one where the customer fails to complete the form
    @Consumer_Contact_Form_01
    @NegativePath @Automated
    Scenario: As a customer trying to submit a contact form, James does not select a question
      Given James is a customer of Bugaboo
      When  James wants to submit a customer contact form
      But   James clicks "Next" without choosing a question
      Then  James should see a warning message

    @Consumer_Contact_Form_02
    @NegativePath @Automated
    Scenario: As a customer of Bugaboo, Ronnie submits a contact form
      Given Ronnie is a customer of Bugaboo
      When  Ronnie wants to submit a customer contact form
      And   Ronnie selects "Delivery, Return or Refund" question
      And   Ronnie submits incorrect form
      Then  Ronnie should see an error message about "missing input"

    @Consumer_Contact_Form_03
    @NegativePath @NotAutomated
    Scenario: As a customer of Bugaboo, Tony submits a contact form
      Given Tony is a customer of Bugaboo
      When  Tony wants to submit a customer contact form
      And   Tony selects "Delivery, Return or Refund" question
      And   Tony fills the contact form properly
      But   Tony does not complete the captcha
      Then  Tony should see an error message about "missing captcha"

    @Consumer_Contact_Form_04
    @NegativePath @Automated
    Scenario: As a customer of Bugaboo, Bruce enters invalid email
      Given Bruce is a customer of Bugaboo
      When  Bruce wants to submit a customer contact form
      And   Bruce selects "Delivery, Return or Refund" question
      But   Bruce enters an invalid email address
      Then  Bruce should see an error message about "invalid email"

    @Consumer_Contact_Form_05
    @NegativePath @Automated
    Scenario: As a customer of Bugaboo, Jared enters a mismatched email address
      Given Jared is a customer of Bugaboo
      When  Jared wants to submit a customer contact form
      And   Jared selects "Delivery, Return or Refund" question
      But   Jared enters an email address that does not match
      Then  Jared should see an error message about "emails not matching"

    @Consumer_Contact_Form_06
    @NegativePath @NotAutomated
    Scenario: As a customer of Bugaboo, Arthur enters invalid phone number
      Given Arthur is a customer of Bugaboo
      When  Arthur wants to submit a customer contact form
      And   Arthur selects "Delivery, Return or Refund" question
      But   Arthur enters an invalid phone number
      Then  Arthur should see a warning message


  Rule: The one where the customer completes the form
    @Consumer_Contact_Form_07
    @PositivePath @NotAutomated
    Scenario Outline: As a customer of Bugaboo, John submits a contact form
      Given John is a customer of Bugaboo
      When  John wants to submit a customer contact form
      And   John selects "<question>" question
      And   John fills the contact form properly
      Then  John should see a message displaying that the request is being processed
      Examples:
        | question                   |
        | Delivery, Return or Refund |
        | Order or Payment Related   |
        | Product Registration       |
        | Product Information        |
        | Maintenance and Usage      |
        | General Question           |

    @Consumer_Contact_Form_08
    @PositivePath @Automated
    Scenario Outline: As a customer of Bugaboo, Hernandez has a question that needs answering
      Given Hernandez is a customer of Bugaboo
      When  Hernandez wants to submit a customer contact form
      And   Hernandez chooses the "<question>" question
      And   Hernandez chooses one of the available options
      Then  Hernandez should see the explanation
      Examples:
        | question                   |
        | Delivery, Return or Refund |
        | Order or Payment Related   |
        | Product Registration       |
        | Product Information        |
        | Maintenance and Usage      |
        | General Question           |