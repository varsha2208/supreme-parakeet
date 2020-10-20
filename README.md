# supreme-parakeet

It’s your first day at work, and you’re in charge of writing the logic to send invitations to set of customers in the city. You need to find the list of customers who can be invited to attend a party based on their location. If they reside within the specified kms of the target location where the party will be hosted, then those set of cutomers must be inivited by provinding their name and id. Well, the output list must be sorted based on customer id.

You are provided with set of customers in a text file, with multiple single-line json entries which provides details such as customer's name, id, latitude and longitude.

The target distance range in kms, the target location's latitude and longitude values are provided. 
You must use the first formula from this Wikipedia article https://en.wikipedia.org/wiki/Great-circle_distance to calculate distance.

Your task is to provide the complete working code along with junits. 
