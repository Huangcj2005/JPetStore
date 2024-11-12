<h3>Account Information</h3>

<table>
    <tr>
        <td>First name:</td>
        <td><input type="text" name="account.firstName" value="${sessionScope.loginAccount.firstName}"></td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td><input type="text" name="account.lastName" value="${sessionScope.loginAccount.lastName}"></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="text" size="40" name="account.email" value="${sessionScope.loginAccount.email}"></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="account.phone" value="${sessionScope.loginAccount.phone}"></td>
    </tr>
    <tr>
        <td>Address 1:</td>
        <td><input type="text" size="40" name="account.address1" value="${sessionScope.loginAccount.address1}"></td>
    </tr>
    <tr>
        <td>Address 2:</td>
        <td><input type="text" size="40" name="account.address2" value="${sessionScope.loginAccount.address2}"></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text" name="account.city" value="${sessionScope.loginAccount.city}"></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" size="4" name="account.state" value="${sessionScope.loginAccount.state}"></td>
    </tr>
    <tr>
        <td>Zip:</td>
        <td><input type="text" size="10" name="account.zip" value="${sessionScope.loginAccount.zip}"></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" size="15" name="account.country" value="${sessionScope.loginAccount.country}"></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td>
            <select name="account.languagePreference">
                <option value="English" <%if(loginAccount.getLanguagePreference().equals("English"))out.println("selected=selected");%>>English</option>
                <option value="Chinese" <%if(loginAccount.getLanguagePreference().equals("Chinese"))out.println("selected=selected");%>>Chinese</option>
            </select>
    </tr>
    <tr>
        <td>Favourite Category:</td>
        <td>
            <select name="account.favouriteCategoryId">
                <option value="BIRDS" <%if(loginAccount.getFavouriteCategoryId().equals("BIRDS"))out.println("selected=selected");%>>birds</option>
                <option value="CATS" <%if(loginAccount.getFavouriteCategoryId().equals("CATS"))out.println("selected=selected");%>>cats</option>
                <option value="DOGS" <%if(loginAccount.getFavouriteCategoryId().equals("DOGS"))out.println("selected=selected");%>>dogs</option>
                <option value="FISH" <%if(loginAccount.getFavouriteCategoryId().equals("FISH"))out.println("selected=selected");%>>fish</option>
                <option value="REPTILES" <%if(loginAccount.getFavouriteCategoryId().equals("REPTILES"))out.println("selected=selected");%>>reptiles</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td><input type="checkbox" name="account.listOption" <%if(loginAccount.isListOption())out.println("checked");%>></td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td><input type="checkbox" name="account.bannerOption" <%if(loginAccount.isBannerOption())out.println("checked");%>></td>
    </tr>

</table>
