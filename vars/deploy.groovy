def call() {
    try {
        // Validate environment
        sh 'docker compose config -q'
        
        // Graceful shutdown
        sh 'docker compose down --timeout 30'
        
        // Start services
        sh 'docker compose up -d'
        
        // Verify deployment
        sh '''
            max_attempts=30
            attempt=1
            while [ $attempt -le $max_attempts ]; do
                if docker compose ps | grep -q "healthy"; then
                    echo "Deployment successful and services are healthy"
                    exit 0
                fi
                echo "Waiting for services to be healthy (attempt $attempt/$max_attempts)..."
                sleep 10
                attempt=$((attempt + 1))
            done
            echo "Services failed to become healthy within timeout"
            exit 1
        '''
    } catch (Exception e) {
        echo "Deployment failed: ${e.message}"
        throw e
    }
}
