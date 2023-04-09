### QAA Assignment
#### Alixhan Basha

The project should work out of the box, but in case it does not I have attached a vide recording of all test cases running in the email, and I have included the "target" folder where the reports are based.

This project has been developed in a Linux ( Fedora 37 Workspace Edition ) environment, and with IntelliJ IDEA. 
For any questions, please write me an email at bashaalixhan@gmail.com

## 1. Bugaboo

### Workflow
Started working at 09:30 PM Friday, ended in around 03:15 AM Saturday
- 45 mins to do a thorough manual black box testing session
- 30 minutes writing what I thought were the most important tests
- 2 hour automating
- Spent around 2 hours trying to find a workaround to captcha and un-interactable elements

### Explanation
- Did black box testing to understand the system
- Created 8 UI scenarios ( did not do more because of the time constraint )
- 5 have been automated, and they are `@Consumer_Contact_Form_01`, `@Consumer_Contact_Form_02`, `@Consumer_Contact_Form_04`, `@Consumer_Contact_Form_05`, `@Consumer_Contact_Form_08` present in `src/test/resources/features/CustomerContactForm.feature`
- Would have automated all of them, but captcha, country dropdown and country number elements did not respond to any of my methods
- Used the ScreenPlay pattern

## 1.1 Explaining the scenarios

- `@Consumer_Contact_Form_01` Click the "Next" button without selecting a question
- `@Consumer_Contact_Form_02` Submit an incomplete form
- `@Consumer_Contact_Form_03` Try to submit a complete form, without solving the captcha
- `@Consumer_Contact_Form_04` Email validation by entering bogus input
- `@Consumer_Contact_Form_05` Make sure that emails must mach
- `@Consumer_Contact_Form_06` Try inserting something other than numbers into the tel input
- `@Consumer_Contact_Form_07` Everything goes right on this one ( Best case )
- `@Consumer_Contact_Form_08` Instead of submitting a form, we find what we are looking for in the quick answers

## 1.2 What are some other possible scenarios?

- Open every form and assert that the elements are present
- Do form verification on all of them
- Send malformed data ( like the invalid email scenario (ex: for inputs that expect a properly formated URL) )
- Complete form creation successfully at least once, for every possible question

## My thoughts on the task
This was a fairly simple task, and I would rank it on the easy scale. However, the presence of some elements that could not be automated made the process more tedious so if I could rank it from a scale of 1 to 10, this would be a solid 4.
`NOTE: My theory in this matter is that the site is trying to prevent bots, and that is why there is a captcha, and some elements like phone number input are not interactable either via the JS browser console, or from the automation. This is the main reason for some of the scenarios not being automated.`


## 2. Yavlena

### Workflow
Started at around 03:00PM Saturday, ended at around 05:00PM Saturday

- 15 minutes to write a few scenarios ( did not spend much time here since the requirements were very clear )
- 1h 45m to implement all the scenarios

### Explanation
- Did black box testing to understand the system
- Created 3 UI scenarios and automated all ( Did not do more, since it was not specifically requested + time constraint )
- Scenarios are `@Yavlena_UI_01`, `@Yavlena_UI_02`, `@Yavlena_UI_03` present in `src/test/resources/features/Yavlena.feature`
- Used the ScreenPlay pattern

## 2.1 Explaining the scenarios

- `@Yavlena_UI_01` Just click the "Load more brokers" button, and wait for the result
- `@Yavlena_UI_02` Search for one broker, and assert the result
- `@Yavlena_UI_03` Search all (102) brokers one by one, and assert the result

## My thoughts on the task
This was a very simple task, I would rate it 1 out of 10 on the difficulty scale.


# Final Thoughts
Overall, both of these tasks were simple. The bugaboo site had some complicated elements that made the automation process more difficult that it should have been. 
If I was a QA Automation Engineer for this project, I would ask the UI devs to create the input elements in a more concise and standard way, rather than what I assume
add some specific conditions that make the element itself unusable in the context of automation.

The second application was a breeze to work on. The UI elements responded as expected, and instead of wasting time on debugging the UI, I was able to spend time implementing
the test cases.
