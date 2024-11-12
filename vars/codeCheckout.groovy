def call(String branch, String url) {
    // Validate branch name format
    if (!branch.matches('^[\\w.-]+$')) {
        error("Invalid branch name format")
    }
    
    // Validate URL format
    if (!url.matches('^https?://[\\w.-]+(/[\\w.-]+)*(\\.git)?$')) {
        error("Invalid git URL format")
    }
    
    try {
        git(
            branch: branch,
            url: url
        )
    } catch (Exception e) {
        error("Failed to checkout code: ${e.message}")
    }
}