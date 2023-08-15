const functions = require("firebase-functions");
const admin = require("firebase-admin");
const googleapis = require('googleapis');
const { JWT } = require('google-auth-library');
const serviceAccount = require('./wordefull-cf277-firebase-adminsdk-nc22e-52a6042be5.json');

admin.initializeApp();

exports.verifyPurchases = functions.https.onRequest(async (request, response) => {
    try {
        const purchaseToken = request.query.purchaseToken;
        const productId = request.query.productId;
        let isValid = false;

        const getAuthorizedClient = () => new JWT({
            email: serviceAccount.client_email,
            key: serviceAccount.private_key,
            scopes: ['https://www.googleapis.com/auth/androidpublisher']
        });

       const playApi = googleapis.google.androidpublisher({
           version: 'v3',
           auth: getAuthorizedClient()
       });

        // Выполнение запроса на проверку покупки
        const checkPurchaseResult = await playApi.purchases.products.get({
            packageName: "com.gamovation.tilecl",
            productId: productId,
            token: purchaseToken
        });

        // Проверка результата запроса
        if (checkPurchaseResult.status === 200) {
            const purchaseState = checkPurchaseResult.data.purchaseState;
            isValid = purchaseState === 0;
        }

        response.send({ isValid });
    } catch (error) {
        console.error("Error verifying purchase:", error);
        response.status(500).send({ error: "Error verifying purchase" });
    }
});
